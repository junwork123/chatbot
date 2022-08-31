package chatbot.client.platform.discord;

import chatbot.client.command.Command;
import chatbot.client.core.ChatBot;
import chatbot.client.core.ChatBotFactory;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.*;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

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
        DiscordChatBot chatBot = new DiscordChatBot(client);

        onCreated(chatBot);
        RegisterCommands(chatBot, commands);
        onDestroy(chatBot);

        return chatBot;
    }

    private void RegisterCommands(DiscordChatBot chatBot, List<Command> commands){
        GatewayDiscordClient client = chatBot.getClient();
        for (Command command : commands) {
            client.getEventDispatcher().on(MessageCreateEvent.class)
                    .map(MessageCreateEvent::getMessage)
                    .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                    .filter(message -> message.getContent().startsWith(command.vo.getStartCommand()))
                    .map(message -> {
                        String response = command.execute(message.getContent());
                        message.getChannel().flatMap(channel -> channel.createMessage(response))
                                .subscribe();
                        return response;
                    })
                    .subscribe();

//            메시지 보내는 방법(지우지 말 것)
//            client.getChannelById(channelId)
//                    .ofType(MessageChannel.class)
//                    .flatMap(channel -> channel.createMessage(message))
//                    .subscribe();
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