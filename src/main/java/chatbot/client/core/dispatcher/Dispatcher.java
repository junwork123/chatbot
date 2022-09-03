package chatbot.client.core.dispatcher;

import chatbot.client.action.Action;
import chatbot.client.message.MessageDto;

import static chatbot.client.utils.ApiUtils.ApiResult;

public interface Dispatcher<T> {
    MessageDto dispatch(T event);
    void onMessage(Action request, ApiResult<MessageDto> result);
}
