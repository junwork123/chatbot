package chatbot.client.controller;

import chatbot.client.domain.ChatBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

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
