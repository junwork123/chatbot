package chatbot.discord.controller;

import chatbot.discord.common.chatbot.ChatBotAdapter;
import chatbot.discord.common.chatbot.ChatBotClient;
import chatbot.discord.common.chatbot.DiscordChatBotAdapter;
import chatbot.discord.common.command.Command;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Slf4j
@Controller
public class ClientController {
    private final List<Command> commands;
    private final GatewayDiscordClient chatBot;
    @Autowired
    public ClientController(ChatBotClient botClient) {
        commands = botClient.getCommandFactory().getCommands();
        DiscordChatBotAdapter botAdapter = new DiscordChatBotAdapter();
        chatBot = botAdapter.adapt(botClient.getChatBotFactory());
    }

    @PostConstruct
    public void RegisterCommands(){
        for (Command command : commands) {
            chatBot.getEventDispatcher().on(MessageCreateEvent.class)
                    .map(MessageCreateEvent::getMessage)
                    .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                    .filter(message -> message.getContent().equalsIgnoreCase(command.startCommand))
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(command.response))
                    .subscribe();
        }
    }

    @PreDestroy
    public void destory(){
        chatBot.onDisconnect().block();
    }
}
