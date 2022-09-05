package chatbot.client.domain.lostArkAuction;

import chatbot.client.core.result.DefaultChatResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class LostArkAuctionServiceImpl implements LostArkAuctionService {
    public DefaultChatResult getAuctionPrices(String content){
        int price = Integer.parseInt(content);
        String responseBody = new StringBuilder()
                .append("4인 기준 : ")
                .append(Math.round(price * 0.71))
                .append("\n8인 기준 : ")
                .append(Math.round(price * 0.83))
                .append("\n16인 기준 : ")
                .append(Math.round(price * 0.89))
                .toString();

        DefaultChatResult result = new DefaultChatResult.Builder()
                .message(responseBody)
                .build();
        return result;
    }
}
