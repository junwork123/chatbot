package chatbot.client.domain;

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


