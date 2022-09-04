package chatbot.client.core;

import chatbot.client.core.action.Action;

import java.util.List;

public interface ChatBotFactory {
    public ChatBot CreateChatBot(List<Action> actions);
}
