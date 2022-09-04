package chatbot.client.core.event;

import chatbot.client.core.action.Action;
import chatbot.client.core.ChatBotController;
import chatbot.client.core.dispatcher.Dispatcher;
import chatbot.client.core.message.MessageDto;
import chatbot.client.platform.discord.DiscordMessageTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static chatbot.client.utils.ApiUtils.*;

@Getter
@Setter
@NoArgsConstructor
public class Event {
    private Dispatcher dispatcher;
    private Action action;
    private ChatBotController controller;

    public Event(Dispatcher dispatcher, Action action, ChatBotController controller) {
        this.dispatcher = dispatcher;
        this.action = action;
        this.controller = controller;
    }

    public void execute(){
        MessageDto messageDto = dispatcher.dispatch(action);
        ApiResult<MessageDto> response = controller.response(DiscordMessageTemplate.TEXT, messageDto.getMessage());
        dispatcher.onMessage(action, response);
    }
}
