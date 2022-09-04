package chatbot.client.platform.discord;

import chatbot.client.core.action.Action;
import chatbot.client.core.action.PredefinedCommand;
import chatbot.client.core.ChatBot;
import chatbot.client.core.message.MessageTemplate;
import chatbot.client.platform.discord.actions.DiscordMessageAction;
import chatbot.client.platform.discord.actions.DiscordVoiceAction;
import chatbot.client.platform.discord.audio.LavaPlayerAudioProvider;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.VoiceChannel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@RequiredArgsConstructor
public class DiscordChatBot implements ChatBot {
    public static final List<MessageTemplate> templates = new ArrayList<>();
    private final GatewayDiscordClient client;
    private final LavaPlayerAudioProvider provider;

    @Override
    @PostConstruct
    public void onCreated() {
        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    User self = event.getSelf();
                    log.info(String.format("logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
                });
        log.info("Ready 이벤트 수신자 설정됨");
    }

    @Override
    public void registerActions(List<Action> actions) {
        // 음성채팅 입장 액션 추가
        Flux<Message> joinFlux = DiscordMessageAction.registerCommand(client, PredefinedCommand.JOIN);
        Flux<VoiceChannel> joinVoiceChannelFlux = new DiscordVoiceAction.Builder()
                .joinVoiceChannel(joinFlux, provider)
                .Build();

        // 음성채팅 퇴장 액션 추가
        Flux<Message> outFlux = DiscordMessageAction.registerCommand(client, PredefinedCommand.OUT);
        Flux<VoiceChannel> leaveVoiceChannelFlux = new DiscordVoiceAction.Builder()
                .leaveVoiceChannel(outFlux)
                .Build();

        // 사용자 정의 액션 추가
        for (Action action : actions) {
            Flux<Message> messageFlux = DiscordMessageAction.registerCommand(client, action);
            messageFlux.subscribe();
        }
    }

    @Override
    public void registerMessageTemplates(List<MessageTemplate> templates) {
        DiscordChatBot.templates.addAll(templates);
    }

    @Override
    @PostConstruct
    public void onDestroy() {
        client.onDisconnect().block();
        log.info("챗봇 종료");
    }
}

