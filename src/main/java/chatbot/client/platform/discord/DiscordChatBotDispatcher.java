package chatbot.client.platform.discord;

import chatbot.client.global.core.DispatchEntity;
import chatbot.client.global.core.dispatcher.ChatBotDispatcher;
import chatbot.client.global.core.model.ChatDto;
import chatbot.client.global.core.model.ChatRequest;
import chatbot.client.global.core.model.ChatResult;
import chatbot.client.global.util.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@Slf4j
@RequiredArgsConstructor
@Component
public class DiscordChatBotDispatcher implements ChatBotDispatcher {
    private static final String MESSENGER = "DISCORD";
    @Override
    public DispatchEntity<ChatRequest> dispatch(ChatDto dto) {
        return DispatchEntity.request(
                dto.toRequestEntity()
                , BeanUtils.findController(dto.command())
        );
    }

    @Override
    public DispatchEntity<ChatResult> onMessage(DispatchEntity<ChatRequest> request) {
        Class<?> clazz = request.controllerMap().keySet().iterator().next();
        Method method = request.controllerMap().get(clazz);
        Object beanInstance = BeanUtils.getBean(clazz);
        ChatResult chatResult;
        try {
            chatResult = (ChatResult) method.invoke(beanInstance, request.content());
            log.info("dispatcher 응답 : " + chatResult.chat().getContent());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return DispatchEntity.response(chatResult);
    }
}
