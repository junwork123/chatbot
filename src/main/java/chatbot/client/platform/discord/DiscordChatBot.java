package chatbot.client.platform.discord;

import chatbot.client.core.ChatBot;
import chatbot.client.core.request.ChatRequest;
import chatbot.client.core.request.MessageDto;
import chatbot.client.core.result.ChatResult;
import chatbot.client.core.result.DefaultChatResult;
import chatbot.client.platform.discord.audio.LavaPlayerAudioProvider;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

import static chatbot.client.utils.ApiUtils.error;
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
    public ApiResult<MessageDto> execute(MessageDto requestDto) {
        ApiResult<ChatRequest> chatRequest = dispatcher.dispatch(requestDto);
        if(chatRequest.isSuccess()){
            Model model = chatRequest.getResponse().getModel();
            model.addAttribute("provider", provider);
            model.addAttribute("client", client);
            ApiResult<ChatResult> chatResult = dispatcher.onMessage(chatRequest.getResponse(), chatRequest.getControllerMap());
            MessageDto resultDto = MessageDto.of(chatResult.getResponse());
            return success(
                    resultDto
                    , null
            );
        }
        return (ApiResult<MessageDto>) error(
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

