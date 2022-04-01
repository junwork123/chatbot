package chatbot.client.controller;

import chatbot.client.common.chatbot.ChatBot;
import chatbot.client.common.chatbot.ChatBotClient;
import chatbot.client.common.command.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Slf4j
@Controller
public class ClientController {
    private final ChatBot chatBot;
    @Autowired
    public ClientController(ChatBotClient botClient) {
        chatBot = botClient.getChatBot();
    }
}
