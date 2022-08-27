package chatbot.client.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PingPongService implements ChatBotService {
    @Override
    public String makeResponse(String option, String content) {
        log.info("option = {}, content = {}", option, content);
        return "Pong!";
    }
}
