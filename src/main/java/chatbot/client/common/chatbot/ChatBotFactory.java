package chatbot.client.common.chatbot;

public interface ChatBotFactory<T> {
    T CreateChatBot();
}
