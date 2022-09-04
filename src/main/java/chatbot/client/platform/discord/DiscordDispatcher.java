package chatbot.client.platform.discord;

import chatbot.client.core.action.Action;
import chatbot.client.core.dispatcher.Dispatcher;
import chatbot.client.core.message.MessageDto;
import chatbot.client.utils.ApiUtils;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class DiscordDispatcher implements Dispatcher<MessageCreateEvent> {
    @Override
    public MessageDto dispatch(MessageCreateEvent event) {
        return null;
    }
    @Override
    public void onMessage(Action request, ApiUtils.ApiResult<MessageDto> result) {

    }
}
