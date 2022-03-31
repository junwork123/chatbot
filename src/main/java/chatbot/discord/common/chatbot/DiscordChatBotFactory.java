package chatbot.discord.common.chatbot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;

import static chatbot.discord.common.Const.DISCORD_TOKEN_ID;

public class DiscordChatBotFactory implements ChatBotFactory<GatewayDiscordClient> {
    @Override
    public GatewayDiscordClient CreateChatBot() {
        return DiscordClientBuilder.create(DISCORD_TOKEN_ID).build().login().block();
    }
}
