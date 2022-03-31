package chatbot.discord.common.chatbot;

public interface ChatBotAdapter<T> {
    boolean supports(ChatBotFactory factory)throws IllegalAccessException;
    T adapt(ChatBotFactory factory);
}
