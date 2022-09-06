package chatbot.client.core.dispatcher;

import chatbot.client.core.command.Command;
import chatbot.client.core.request.ChatRequest;
import chatbot.client.core.request.MessageDto;
import chatbot.client.core.result.ChatResult;

import java.lang.reflect.Method;
import java.util.Map;

import static chatbot.client.utils.ApiUtils.ApiResult;

public interface Dispatcher {
    ApiResult<ChatRequest> dispatch(MessageDto dto);
    ApiResult<ChatResult> onMessage(ChatRequest request, Map<Class<?>, Method> map);
}
