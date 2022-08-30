package chatbot.client.platform.discord;

import chatbot.client.core.ChatBot;
import discord4j.core.GatewayDiscordClient;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class DiscordChatBot implements ChatBot {
    private final GatewayDiscordClient client;
    public DiscordChatBot(GatewayDiscordClient client) {
        this.client = client;
    }

}

