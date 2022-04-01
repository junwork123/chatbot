package chatbot.client.common.chatbot;

import chatbot.client.common.command.Command;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

import java.util.List;

import static chatbot.client.common.Const.DISCORD_TOKEN_ID;

public class DiscordChatBotFactory implements ChatBotFactory {
    @Override
    public ChatBot CreateChatBot(List<Command> commands) {
        DiscordChatBot chatBot = new DiscordChatBot();
        chatBot.client = DiscordClientBuilder.create(DISCORD_TOKEN_ID).build().login().block();

        RegisterCommands(chatBot.client, commands);
        onCreated(chatBot.client);
        onDestroy(chatBot.client);
        return chatBot;
    }

    private void RegisterCommands(GatewayDiscordClient client, List<Command> commands){
        for (Command command : commands) {
            client.getEventDispatcher().on(MessageCreateEvent.class)
                    .map(MessageCreateEvent::getMessage)
                    .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                    .filter(message -> message.getContent().equalsIgnoreCase(command.startCommand))
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(command.response))
                    .subscribe();
        }
    }
    private void onCreated(GatewayDiscordClient client) {

    }
    private void onDestroy(GatewayDiscordClient client) {
        client.onDisconnect().block();
    }
}
