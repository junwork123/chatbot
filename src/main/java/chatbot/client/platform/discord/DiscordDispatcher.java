package chatbot.client.platform.discord;

import chatbot.client.core.dispatcher.Dispatcher;
import chatbot.client.core.chat.ChatRequest;
import chatbot.client.core.chat.ChatDto;
import chatbot.client.core.chat.ChatResult;
import chatbot.client.utils.BeanUtils;
import chatbot.client.utils.ChatBotUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import static chatbot.client.utils.ApiUtils.ApiResult;
import static chatbot.client.utils.ApiUtils.success;
@Slf4j
@RequiredArgsConstructor
@Component
public class DiscordDispatcher implements Dispatcher {
    private static final String MESSENGER = "DISCORD";
    @Override
    public ApiResult<ChatRequest> dispatch(ChatDto dto) {
        return success(
                dto.toRequestEntity()
                , ChatBotUtils.findController(dto.getCommand())
        );
    }

    @Override
    public ApiResult<ChatResult> onMessage(ChatRequest request, Map<Class<?>, Method> controllerMap) {
        Class<?> clazz = controllerMap.keySet().stream().findFirst().get();
        Method method = controllerMap.get(clazz);
        Object beanInstance = BeanUtils.getBean(clazz);
        ChatResult chatResult = null;
        try {
            chatResult = (ChatResult) method.invoke(beanInstance, request);
            log.info("dispatcher 응답 : " + chatResult.getChat().getContent());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return success(
                chatResult
                , null
        );
    }
}
