package chatbot.client.common.chatbot;

import discord4j.core.GatewayDiscordClient;
import lombok.Getter;
import lombok.Setter;

@Getter
public class DiscordChatBot implements ChatBot{
    private final GatewayDiscordClient client;
    public DiscordChatBot(GatewayDiscordClient client) {
        this.client = client;
    }
    @Override
    public boolean hasCommands() {
        return false;
    }

    @Override
    public void publish() {

    }

    @Override
    public String response(String command) {
        return "";
    }
}
