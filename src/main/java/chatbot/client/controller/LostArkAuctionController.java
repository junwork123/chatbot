package chatbot.client.controller;

import chatbot.client.service.LostArkAuctionService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class LostArkAuctionController implements ChatBotController{
    @Autowired
    private final LostArkAuctionService service = new LostArkAuctionService();
    @Override
    public String response(String option, @NonNull String content) {
        return service.makeResponse(option, content);
    }
}
