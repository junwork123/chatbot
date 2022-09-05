package chatbot.client.domain.lostArkAuction;

import chatbot.client.core.ChatBotController;
import chatbot.client.core.command.CommandMapping;
import chatbot.client.core.result.DefaultChatResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ChatBotController
public class LostArkAuctionController{
    private final LostArkAuctionService service;
    @CommandMapping(startCommand = "입찰")
    public DefaultChatResult auction(String content){
        log.info("로아 경매 컨트롤러");
        return service.getAuctionPrices(content);
    }
}
