package chatbot.client.common.chatbot;

import chatbot.client.service.ChatBotService;
import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
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


