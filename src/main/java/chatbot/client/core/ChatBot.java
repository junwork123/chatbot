package chatbot.client.core;

import chatbot.client.core.request.MessageDto;
import chatbot.client.core.result.ChatResult;
import static chatbot.client.utils.ApiUtils.*;

public interface ChatBot {
    public void onCreated();
    public ApiResult<MessageDto> execute(MessageDto messageDto);
    public void onDestroy();

}
