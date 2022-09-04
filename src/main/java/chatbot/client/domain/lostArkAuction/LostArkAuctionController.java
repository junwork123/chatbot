package chatbot.client.domain.lostArkAuction;

import chatbot.client.core.ChatBotController;
import chatbot.client.core.Answerable;
import chatbot.client.core.message.MessageDto;
import chatbot.client.core.message.MessageTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.google.common.base.Preconditions.checkNotNull;
import static chatbot.client.utils.ApiUtils.*;

@Slf4j
@RequiredArgsConstructor
@ChatBotController
public class LostArkAuctionController implements Answerable {
    private final LostArkAuctionServiceImpl service;

    @Override
    public ApiResult<MessageDto> response(MessageTemplate template, String content) {
        return success(
                service.makeResponse(template, content)
                        .map(MessageDto::new)
                        .orElseThrow(() -> new RuntimeException("Message Error"))
        );
    }
}
