package chatbot.client.core.dispatcher;

import chatbot.client.core.chat.ChatRequest;
import chatbot.client.core.chat.ChatDto;
import chatbot.client.core.chat.ChatResult;

import java.lang.reflect.Method;
import java.util.Map;

import static chatbot.client.utils.ApiUtils.ApiResult;

public interface Dispatcher {
    ApiResult<ChatRequest> dispatch(ChatDto dto);
    ApiResult<ChatResult> onMessage(ChatRequest request, Map<Class<?>, Method> map);
}
