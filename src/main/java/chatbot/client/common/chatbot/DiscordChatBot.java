package chatbot.client.common.chatbot;

import discord4j.core.GatewayDiscordClient;

public class DiscordChatBot implements ChatBot{
    GatewayDiscordClient client;
    @Override
    public boolean hasCommands() {
        return false;
    }

    @Override
    public void publish() {

    }

    @Override
    public void response() {

    }
}
