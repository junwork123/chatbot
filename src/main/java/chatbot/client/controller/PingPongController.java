package chatbot.client.controller;

import chatbot.client.domain.MessageDto;
import chatbot.client.domain.MessageTemplate;
import chatbot.client.service.PingPongService;
import chatbot.client.utils.ApiUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static chatbot.client.utils.ApiUtils.*;
import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
public class PingPongController implements ChatBotController{
    @Autowired
    private final PingPongService service = new PingPongService();

    @Override
    public ApiResult<MessageDto> response(MessageTemplate template, @NonNull String content) {
        return success(
                service.makeResponse(template, content)
                        .map(MessageDto::new)
                        .orElseThrow(() -> new RuntimeException("Message Error"))
        );
    }
}
