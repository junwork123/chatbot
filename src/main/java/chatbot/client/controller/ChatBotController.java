package chatbot.client.controller;

import chatbot.client.common.chatbot.ChatBot;
import chatbot.client.common.chatbot.ChatBotFactory;
import chatbot.client.common.command.Command;
import chatbot.client.common.command.CommandBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@EnableScheduling
@RequiredArgsConstructor
@Controller
public class ChatBotController {
    private final ChatBot chatBot;

//    @RequestMapping
//    public String hello(){
//        return "hello";
//    }
//
//    @GetMapping("hello")
//    public void awaken(){
//        log.info("waked Up! : {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//    }
//    @PostMapping("hello")
//    public void startSchedule() throws IOException {
//        noSleep();
//    }
//
//    @Scheduled(cron = "10 * * * * *")
//    public void noSleep() {
//        wakeUpSelf();
//        wakeUpSelfLocal();
//    }
//    public void wakeUpSelf(){
//        WebClient.create("https://chatbot-junwork.herokuapp.com/").get();
//    }
//    public void wakeUpSelfLocal(){
//        WebClient.create("https://localhost:8080/").get();
//    }


}
