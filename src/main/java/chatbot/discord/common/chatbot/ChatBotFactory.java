package chatbot.discord.common.chatbot;

public interface ChatBotFactory<T> {
    T CreateChatBot();
}
