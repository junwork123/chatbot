package chatbot.client.common.chatbot;

import chatbot.client.common.command.Command;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.*;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static chatbot.client.common.Const.DISCORD_TOKEN_ID;

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
                    //.subscribe(message -> chatBot.request(message));

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
