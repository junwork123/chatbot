package chatbot.discord.controller;

import chatbot.discord.common.chatbot.ChatBotClient;
import chatbot.discord.common.chatbot.DiscordChatBotFactory;
import chatbot.discord.common.command.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Controller
public class ClientController {
    private final ChatBotClient botClient;

    @Autowired
    public ClientController(ChatBotClient botClient) {
        this.botClient = botClient;
    }

    @PostConstruct
    public void RegisterCommands(){
        for (Command command : botClient.getCommands()) {
            log.info("{} : {}", this.getClass(), "실행됨");
//            botClient.getChatBot().getEventDispatcher().on(MessageCreateEvent.class)
//                    .map(MessageCreateEvent::getMessage)
//                    .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
//                    .filter(message -> message.getContent().equalsIgnoreCase("!ping"))
//                    .flatMap(Message::getChannel)
//                    .flatMap(channel -> channel.createMessage("Pong!"))
//                    .subscribe();
        }
    }

    @PreDestroy
    public void destory(){
//        DiscordChatBot chatBot = botClient.getChatBotFactory();
//        chatBot.onDisconnect().block();
    }
}
