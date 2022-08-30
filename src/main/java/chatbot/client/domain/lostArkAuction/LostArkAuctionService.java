package chatbot.client.domain.lostArkAuction;

import chatbot.client.core.ChatBotService;
import chatbot.client.message.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static java.util.Optional.ofNullable;
import static com.google.common.base.Preconditions.checkNotNull;
@Slf4j
@Service
public class LostArkAuctionService implements ChatBotService {
    @Override
    public Optional<MessageTemplate> makeResponse(MessageTemplate template, String content) {
        log.info("content = {}", content);
        checkNotNull(content, "가격이 제시되지 않았습니다");
        int price = Integer.parseInt(content);
        String responseBody = new StringBuilder()
                                        .append("4인 기준 : ")
                                        .append(Math.round(price * 0.71))
                                        .append(", 8인 기준 : ")
                                        .append(Math.round(price * 0.83))
                                        .append(", 16인 기준 : ")
                                        .append(Math.round(price * 0.89))
                                        .toString();
        template.convert(responseBody);
        return ofNullable(template.isEmpty() ? null : template);
    }
}
