package chatbot.client.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LostArkAuctionService implements ChatBotService {
    @Override
    public String doService(String option, String content) {
        log.info("option = {}, content = {}", option, content);
        int price = Integer.parseInt(content);
        return new StringBuilder()
                .append("4인 기준 : ")
                .append(Math.round(price * 0.71))
                .append(", 8인 기준 : ")
                .append(Math.round(price * 0.83))
                .append(", 16인 기준 : ")
                .append(Math.round(price * 0.89))
                .toString();
    }
}
