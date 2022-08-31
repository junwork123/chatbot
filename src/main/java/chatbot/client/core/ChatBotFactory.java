package chatbot.client.core;

import chatbot.client.action.Action;

import java.util.List;

public interface ChatBotFactory {
    public ChatBot CreateChatBot(List<Action> actions);
}
