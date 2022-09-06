package chatbot.client.core;

import chatbot.client.core.request.MessageDto;

import static chatbot.client.utils.ApiUtils.ApiResult;

public interface ChatBot {
    public void onCreated();
    public void registerSensor();
    public ApiResult<MessageDto> execute(MessageDto messageDto);
    public void onDestroy();

}
