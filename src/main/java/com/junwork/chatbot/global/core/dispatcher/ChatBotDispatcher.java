package com.junwork.chatbot.global.core.dispatcher;

import com.junwork.chatbot.global.core.DispatchEntity;
import com.junwork.chatbot.global.core.model.ChatDto;
import com.junwork.chatbot.global.core.model.ChatRequest;
import com.junwork.chatbot.global.core.model.ChatResult;


public interface ChatBotDispatcher {
    DispatchEntity<ChatRequest> dispatch(ChatDto dto);
    DispatchEntity<ChatResult> onMessage(DispatchEntity<ChatRequest> request);
}
