package chatbot.client.domain.lostArkAuction;

import chatbot.client.core.ChatBotController;
import chatbot.client.message.MessageDto;
import chatbot.client.message.MessageTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkNotNull;
import static chatbot.client.utils.ApiUtils.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LostArkAuctionController implements ChatBotController {
    private final LostArkAuctionService service;

    @Override
    public ApiResult<MessageDto> response(MessageTemplate template, String content) {
        return success(
                service.makeResponse(template, content)
                        .map(MessageDto::new)
                        .orElseThrow(() -> new RuntimeException("Message Error"))
        );
    }
}
