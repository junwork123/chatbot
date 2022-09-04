package chatbot.client.core;

import chatbot.client.core.message.MessageDto;
import chatbot.client.core.message.MessageTemplate;
import lombok.NonNull;

import static chatbot.client.utils.ApiUtils.ApiResult;

public interface Responsible {
    public ApiResult<MessageDto> response(@NonNull MessageTemplate template, @NonNull String content);
}
