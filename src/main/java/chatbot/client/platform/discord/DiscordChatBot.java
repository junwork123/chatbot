package chatbot.client.platform.discord;

import chatbot.client.core.ChatBot;
import chatbot.client.core.command.Command;
import chatbot.client.core.chat.ChatRequest;
import chatbot.client.core.chat.ChatDto;
import chatbot.client.core.chat.ChatResult;
import chatbot.client.platform.discord.EventSensor.DiscordMessageEventSensor;
import chatbot.client.platform.discord.EventSensor.DiscordVoiceEventSensor;
import chatbot.client.platform.discord.audio.LavaPlayerAudioProvider;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.VoiceChannel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

import static chatbot.client.utils.ApiUtils.*;
@Slf4j
@Getter
@RequiredArgsConstructor
public class DiscordChatBot implements ChatBot {
    private final GatewayDiscordClient client;
    private final LavaPlayerAudioProvider provider;
    private final DiscordDispatcher dispatcher;

    @Override
    public void onCreated() {
        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    User self = event.getSelf();
                    log.info(String.format("logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
                });
        log.info("Ready 이벤트 수신자 설정됨");
    }

    @Override
    public void registerSensor() {
        for (Command command : Command.values()) {
            Flux<Message> flux = DiscordMessageEventSensor.registerCommand(this, command);
            flux.subscribe();
        }

        // 음성채팅 입장 이벤트 추가
        Flux<Message> joinFlux = DiscordMessageEventSensor.registerCommand(this, Command.JOIN);
        Flux<VoiceChannel> joinVoiceChannelFlux = new DiscordVoiceEventSensor.Builder()
                .joinVoiceChannel(joinFlux, provider)
                .Build();

        // 음성채팅 퇴장 액션 추가
        Flux<Message> outFlux = DiscordMessageEventSensor.registerCommand(this, Command.OUT);
        Flux<VoiceChannel> leaveVoiceChannelFlux = new DiscordVoiceEventSensor.Builder()
                .leaveVoiceChannel(outFlux)
                .Build();
    }

    @Override
    public ApiResult<ChatDto> execute(ChatDto requestDto) {
        log.info("dto 모델 : " + requestDto.getModel());
        ApiResult<ChatRequest> chatRequest = dispatcher.dispatch(requestDto);
        if(chatRequest.isSuccess()){
            Model model = chatRequest.getResponse().getModel();
            log.info("dispatch 모델 : " + model);
            model.addAttribute("provider", provider);
            model.addAttribute("client", client);
            ApiResult<ChatResult> chatResult = dispatcher.onMessage(chatRequest.getResponse(), chatRequest.getControllerMap());
            log.info("dispatch 모델 : " + chatResult.getResponse());
            ChatDto resultDto = ChatDto.of(chatResult.getResponse());
            return success(
                    resultDto
                    , null
            );
        }
        return (ApiResult<ChatDto>) error(
                new IllegalArgumentException()
                , HttpStatus.NOT_FOUND
        );

    }

    @Override
    @PostConstruct
    public void onDestroy() {
        client.onDisconnect().block();
        log.info("챗봇 종료");
    }
}

