package chatbot.client.platform.discord;

import chatbot.client.global.common.ApiResult;
import chatbot.client.global.common.ApiResult.ApiEntity;
import chatbot.client.global.core.ChatBot;
import chatbot.client.global.core.DispatchEntity;
import chatbot.client.global.core.model.ChatDto;
import chatbot.client.global.core.model.ChatRequest;
import chatbot.client.global.core.model.ChatResult;
import chatbot.client.global.core.command.Command;
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
import org.springframework.ui.Model;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
@Slf4j
@Getter
@RequiredArgsConstructor
public class DiscordChatBot implements ChatBot {
    private final GatewayDiscordClient client;
    private final LavaPlayerAudioProvider provider;
    private final DiscordChatBotDispatcher dispatcher;

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
            // 클라이언트 액션이 필요한 경우는 제외하고 등록
            if(!command.isAction()) {
                Flux<Message> flux = DiscordMessageEventSensor.registerCommand(this, command);
                flux.subscribe();
            }
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
    public ApiEntity<?> execute(ChatDto requestDto) {
        log.info("dto 모델 : " + requestDto.model());
        DispatchEntity<ChatRequest> request = dispatcher.dispatch(requestDto);

        Model model = request.content().model();
        log.info("dispatch 모델 : " + model);
        model.addAttribute("provider", provider);
        model.addAttribute("client", client);

        DispatchEntity<ChatResult> chatResult = dispatcher.onMessage(request);
        log.info("dispatch 결과 : " + chatResult.content());

        ChatDto resultDto = ChatDto.of(chatResult.content());
        return ApiResult.success(resultDto);
    }

    @Override
    @PostConstruct
    public void onDestroy() {
        client.onDisconnect().block();
        log.info("챗봇 종료");
    }
}

