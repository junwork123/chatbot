package chatbot.client.domain.pingpong;

import chatbot.client.core.ChatBotController;
import chatbot.client.core.message.MessageDto;
import chatbot.client.core.message.MessageTemplate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import static chatbot.client.utils.ApiUtils.*;
import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PingPongController implements ChatBotController {
    private final PingPongService service;

    @Override
    public ApiResult<MessageDto> response(MessageTemplate template, @NonNull String content) {
        return success(
                service.makeResponse(template, content)
                        .map(MessageDto::new)
                        .orElseThrow(() -> new RuntimeException("Message Error"))
        );
    }
}
