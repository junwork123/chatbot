package chatbot.client.domain.lostArkAuction;

import chatbot.client.core.chat.Chat;
import chatbot.client.core.chat.ChatResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class LostArkAuctionServiceImpl implements LostArkAuctionService {
    public ChatResult getAuctionPrices(String content){
        int price = Integer.parseInt(content);
        String responseBody = new StringBuilder()
                .append("4인 기준 : ")
                .append(Math.round(price * 0.71))
                .append("\n8인 기준 : ")
                .append(Math.round(price * 0.83))
                .append("\n16인 기준 : ")
                .append(Math.round(price * 0.89))
                .toString();

        Chat chat = Chat.builder()
                .content(responseBody)
                .build();

        return ChatResult.builder()
                .chat(chat)
                .build();
    }
}
