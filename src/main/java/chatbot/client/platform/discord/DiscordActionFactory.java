package chatbot.client.platform.discord;

import chatbot.client.action.ActionFactory;
import chatbot.client.action.ActionKind;
import chatbot.client.action.Action;
import chatbot.client.platform.discord.actions.DiscordMessageAction;
import chatbot.client.platform.discord.actions.DiscordVoiceAction;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.VoiceChannel;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class DiscordActionFactory implements ActionFactory {
    public static void init(DiscordChatBot chatBot, List<Action> actions){
        onCreated(chatBot);
        registerActions(chatBot, actions);
        onDestroy(chatBot);
    }
    private static void registerActions(DiscordChatBot chatBot, List<Action> actions) {
        GatewayDiscordClient client = chatBot.getClient();

        // 음성채팅 입장 액션 추가
        Flux<Message> joinFlux = DiscordMessageAction.registerCommand(client, ActionKind.JOIN);
        Flux<VoiceChannel> joinVoiceChannelFlux = new DiscordVoiceAction.Builder()
                .joinVoiceChannel(joinFlux)
                .Build();

        // 음성채팅 퇴장 액션 추가
        Flux<Message> outFlux = DiscordMessageAction.registerCommand(client, ActionKind.OUT);
        Flux<VoiceChannel> leaveVoiceChannelFlux = new DiscordVoiceAction.Builder()
                .leaveVoiceChannel(outFlux)
                .Build();

        // 사용자 정의 액션 추가
        for (Action action : actions) {
            Flux<Message> messageFlux = DiscordMessageAction.registerCommand(client, action);
            messageFlux.subscribe();
        }
    }
    private static void onCreated(DiscordChatBot chatBot) {
        chatBot.getClient().getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    User self = event.getSelf();
                    log.info(String.format("logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
                });
        log.info("Ready 이벤트 수신자 설정됨");
    }
    private static void onDestroy(DiscordChatBot chatBot) {
        chatBot.getClient().onDisconnect().block();
    }
}
