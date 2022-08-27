package chatbot.client.controller;

import chatbot.client.service.PingPongService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class PingPongController implements ChatBotController{
    @Autowired
    private final PingPongService service = new PingPongService();
    @Override
    public String response(String option, @NonNull String content) {
        return service.makeResponse(option, content);
    }
}
