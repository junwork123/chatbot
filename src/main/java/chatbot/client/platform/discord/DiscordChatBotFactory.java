package chatbot.client.platform.discord;

import chatbot.client.action.ActionKind;
import chatbot.client.command.Command;
import chatbot.client.core.ChatBot;
import chatbot.client.core.ChatBotFactory;
import chatbot.client.platform.discord.actions.DiscordMessageAction;
import chatbot.client.platform.discord.actions.DiscordVoiceAction;
import chatbot.client.platform.discord.audioProvider.LavaPlayerAudioProvider;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.VoiceChannel;
import discord4j.voice.AudioProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * 로아봇 상태에 따른 이벤트 종류
 * ConnectEvent
 * DisconnectEvent
 * ReadyEvent
 * ReconnectEvent
 * ReconnectFailEvent
 * ResumeEvent
 * SessionInvalidatedEvent
 */
@Slf4j
public class DiscordChatBotFactory implements ChatBotFactory {
    @Value("${DISCORD_TOKEN_ID}")
    private String DISCORD_TOKEN_ID;

    @Override
    public ChatBot CreateChatBot(List<Command> commands) {
        GatewayDiscordClient client = DiscordClientBuilder.create(DISCORD_TOKEN_ID).build().login().block();
        AudioProvider provider = LavaPlayerAudioProvider.createAudioProvider();

        DiscordChatBot chatBot = new DiscordChatBot(client, provider);

        onCreated(chatBot);
        registerActions(chatBot);
        registerCommands(chatBot, commands);
        onDestroy(chatBot);

        return chatBot;
    }

    private void registerActions(DiscordChatBot chatBot) {
        GatewayDiscordClient client = chatBot.getClient();

        Flux<Message> joinFlux = new DiscordMessageAction.Builder()
                .getMessageFromClient(client)
                .filterCommand(ActionKind.JOIN.getStartCommand())
                .createMessage(ActionKind.JOIN.getDisplayMessage())
                .Build();

        Flux<VoiceChannel> joinVoiceChannelFlux = new DiscordVoiceAction.Builder()
                                                                .joinVoiceChannel(joinFlux)
                                                                .Build();

        Flux<Message> outFlux = new DiscordMessageAction.Builder()
                .getMessageFromClient(client)
                .filterCommand(ActionKind.OUT.getStartCommand())
                .createMessage(ActionKind.OUT.getDisplayMessage())
                .Build();

        Flux<VoiceChannel> leaveVoiceChannelFlux = new DiscordVoiceAction.Builder()
                                                                .leaveVoiceChannel(outFlux)
                                                                .Build();
    }

    private void registerCommands(DiscordChatBot chatBot, List<Command> commands){
        GatewayDiscordClient client = chatBot.getClient();
        for (Command command : commands) {
            Flux<Message> messageFlux = new DiscordMessageAction.Builder()
                    .getMessageFromClient(client)
                    .filterCommand(command.vo.getStartCommand())
                    .executeCommand(command)
                    .Build();
            messageFlux.subscribe();
        }
    }

    private void onCreated(DiscordChatBot chatBot) {
        chatBot.getClient().getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    User self = event.getSelf();
                    log.info(String.format("logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
                });
        log.info("{} : {}", this.getClass(), "Ready 이벤트 수신자 설정됨");
    }
    private void onDestroy(DiscordChatBot chatBot) {
        chatBot.getClient().onDisconnect().block();
    }
}
