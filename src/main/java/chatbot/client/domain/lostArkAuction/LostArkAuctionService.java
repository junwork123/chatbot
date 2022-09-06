package chatbot.client.domain.lostArkAuction;

import chatbot.client.core.chat.ChatResult;

public interface LostArkAuctionService {
    public ChatResult getAuctionPrices(String content);
}
