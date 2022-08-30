package chatbot.client.core;

import chatbot.client.message.MessageDto;
import chatbot.client.message.MessageTemplate;
import lombok.NonNull;
import org.springframework.stereotype.Controller;

import static chatbot.client.utils.ApiUtils.ApiResult;

@Controller
public interface ChatBotController {
    public ApiResult<MessageDto> response(@NonNull MessageTemplate template, @NonNull String content);
}
