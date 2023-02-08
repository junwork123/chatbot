package chatbot.client.global.core;

import chatbot.client.global.core.model.ChatDto;

import static chatbot.client.global.common.ApiResult.*;

public interface ChatBot {
    void onCreated();
    void registerSensor();
    ApiEntity<?> execute(ChatDto chatDto);
    void onDestroy();

}
