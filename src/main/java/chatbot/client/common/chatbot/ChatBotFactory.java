package chatbot.client.common.chatbot;

import chatbot.client.common.command.Command;

import java.util.List;

public interface ChatBotFactory {
    public ChatBot CreateChatBot(List<Command> commands);
}
