package chatbot.client.core;

import chatbot.client.command.Command;

import java.util.List;

public interface ChatBotFactory {
    public ChatBot CreateChatBot(List<Command> commands);
}
