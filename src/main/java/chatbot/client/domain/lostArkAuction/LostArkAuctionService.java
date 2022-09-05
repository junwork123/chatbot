package chatbot.client.domain.lostArkAuction;

import chatbot.client.core.result.DefaultChatResult;

public interface LostArkAuctionService {
    public DefaultChatResult getAuctionPrices(String content);
}
