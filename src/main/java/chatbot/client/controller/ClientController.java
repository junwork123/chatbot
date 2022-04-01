package chatbot.client.controller;

import chatbot.client.common.chatbot.ChatBot;
import chatbot.client.common.chatbot.ChatBotClient;
import chatbot.client.common.command.Command;
import chatbot.client.service.LostArkService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Slf4j
@RequestMapping("/")
@RestController
public class ClientController {
    private final ChatBot chatBot;
    @Autowired
    public ClientController(ChatBotClient botClient) {
        chatBot = botClient.getChatBot();
    }

//    @GetMapping("/입찰/{price}")
//    public String hello(@RequestParam int price){
//        //71%(4인)/83%(8인)/89%(16인)
//        return new StringBuilder()
//                .append("4인 기준 : ")
//                .append(Math.round(price * 0.71))
//                .append("8인 기준 : ")
//                .append(Math.round(price * 0.83))
//                .append("16인 기준 : ")
//                .append(Math.round(price * 0.89))
//                .toString();
//    }
}
