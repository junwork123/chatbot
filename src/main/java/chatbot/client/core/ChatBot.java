package chatbot.client.core;

import chatbot.client.core.chat.ChatDto;

import static chatbot.client.utils.ApiUtils.ApiResult;

public interface ChatBot {
    public void onCreated();
    public void registerSensor();
    public ApiResult<ChatDto> execute(ChatDto chatDto);
    public void onDestroy();

}
