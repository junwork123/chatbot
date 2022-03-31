package chatbot.discord.common.chatbot;

import discord4j.core.GatewayDiscordClient;

public interface ChatBotAdapter<T> {
    boolean supports(ChatBotFactory factory)throws IllegalAccessException;
    T adapt(ChatBotFactory factory);
}
