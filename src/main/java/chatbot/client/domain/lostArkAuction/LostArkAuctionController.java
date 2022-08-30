package chatbot.client.domain.lostArkAuction;

import chatbot.client.core.ChatBotController;
import chatbot.client.message.MessageDto;
import chatbot.client.message.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.google.common.base.Preconditions.checkNotNull;
import static chatbot.client.utils.ApiUtils.*;

@Slf4j
public class LostArkAuctionController implements ChatBotController {
    @Autowired
    private final LostArkAuctionService service = new LostArkAuctionService();

    @Override
    public ApiResult<MessageDto> response(MessageTemplate template, String content) {
        return success(
                service.makeResponse(template, content)
                        .map(MessageDto::new)
                        .orElseThrow(() -> new RuntimeException("Message Error"))
        );
    }
}
