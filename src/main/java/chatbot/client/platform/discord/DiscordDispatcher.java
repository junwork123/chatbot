package chatbot.client.platform.discord;

import chatbot.client.core.command.Command;
import chatbot.client.core.dispatcher.Dispatcher;
import chatbot.client.core.request.ChatRequest;
import chatbot.client.core.request.MessageDto;
import chatbot.client.core.result.ChatResult;
import chatbot.client.core.result.DefaultChatResult;
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
    public ApiResult<ChatRequest> dispatch(MessageDto dto) {
        return success(
                ChatRequest.builder()
                        .messenger(MESSENGER)
                        .command(dto.getCommand())
                        .content(ChatBotUtils.parseCommand(dto.getContent(), dto.getCommand()))
                        .build()
                , ChatBotUtils.findController(dto.getCommand())
        );
    }

    @Override
    public ApiResult<ChatResult> onMessage(ChatRequest request, Map<Class<?>, Method> map) {
        Class<?> clazz = map.keySet().stream().findFirst().get();
        Method method = map.get(clazz);
        Object beanInstance = BeanUtils.getBean(clazz);
        DefaultChatResult chatResult = null;
        try {
            chatResult = (DefaultChatResult) method.invoke(beanInstance, request.getContent());
            log.info("invoke 직후 : " + chatResult.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return success(
                chatResult
                , null
        );
    }
}
