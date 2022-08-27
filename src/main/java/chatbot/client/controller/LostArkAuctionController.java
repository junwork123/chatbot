package chatbot.client.controller;

import chatbot.client.domain.DiscordMessageTemplate;
import chatbot.client.domain.MessageDto;
import chatbot.client.domain.MessageTemplate;
import chatbot.client.service.LostArkAuctionService;
import chatbot.client.utils.ApiUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.google.common.base.Preconditions.checkNotNull;
import static chatbot.client.utils.ApiUtils.*;

@Slf4j
public class LostArkAuctionController implements ChatBotController{
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
