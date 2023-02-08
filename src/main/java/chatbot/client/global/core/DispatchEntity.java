package chatbot.client.global.core;

import chatbot.client.global.core.model.ChatRequest;
import chatbot.client.global.core.model.ChatResult;

import java.lang.reflect.Method;
import java.util.Map;

public record DispatchEntity<T> (T content, Map<Class<?>, Method> controllerMap){
    public static DispatchEntity<ChatRequest> request(ChatRequest request, Map<Class<?>, Method> controllerMap) {
        return new DispatchEntity<>(request, controllerMap);
    }
    public static DispatchEntity<ChatResult> response(ChatResult result) {
        return new DispatchEntity<>(result, null);
    }
}
