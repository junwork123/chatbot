package chatbot.client.global.core.dispatcher;

import chatbot.client.global.core.DispatchEntity;
import chatbot.client.global.core.model.ChatDto;
import chatbot.client.global.core.model.ChatRequest;
import chatbot.client.global.core.model.ChatResult;


public interface ChatBotDispatcher {
    DispatchEntity<ChatRequest> dispatch(ChatDto dto);
    DispatchEntity<ChatResult> onMessage(DispatchEntity<ChatRequest> request);
}
