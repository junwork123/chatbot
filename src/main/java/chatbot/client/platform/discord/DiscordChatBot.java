package chatbot.client.platform.discord;

import chatbot.client.core.ChatBot;
import discord4j.core.GatewayDiscordClient;
import discord4j.voice.AudioProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public class DiscordChatBot implements ChatBot {
    private final GatewayDiscordClient client;
    private final AudioProvider provider;
}

