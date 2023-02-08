package com.junwork.chatbot.global.core;

import com.junwork.chatbot.global.core.model.ChatDto;
import com.junwork.chatbot.global.common.ApiResult;

public interface ChatBot {
    void onCreated();
    void registerSensor();
    ApiResult.ApiEntity<?> execute(ChatDto chatDto);
    void onDestroy();

}
