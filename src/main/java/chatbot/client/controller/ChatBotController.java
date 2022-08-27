package chatbot.client.controller;

import chatbot.client.domain.MessageDto;
import chatbot.client.domain.MessageTemplate;
import lombok.NonNull;
import org.springframework.stereotype.Controller;

import static chatbot.client.utils.ApiUtils.ApiResult;

@Controller
public interface ChatBotController {
    public ApiResult<MessageDto> response(@NonNull MessageTemplate template, @NonNull String content);
}
