package chatbot.client.platform.discord;

import chatbot.client.global.core.ChatBot;
import chatbot.client.global.core.ChatBotFactory;
import chatbot.client.platform.discord.audio.LavaPlayerAudioProvider;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 로아봇 상태에 따른 이벤트 종류
 * ConnectEvent
 * DisconnectEvent
 * ReadyEvent
 * ReconnectEvent
 * ReconnectFailEvent
 * ResumeEvent
 * SessionInvalidatedEvent
 */
@Slf4j
@Component
public class DiscordChatBotFactory implements ChatBotFactory {
    @Value("${DISCORD_TOKEN_ID}")
    private String DISCORD_TOKEN_ID;
    @Override
    public ChatBot CreateChatBot() {
        // Discord 챗봇 생성
        GatewayDiscordClient client = DiscordClientBuilder.create(DISCORD_TOKEN_ID).build().login().block();
        LavaPlayerAudioProvider provider = new LavaPlayerAudioProvider();
        DiscordChatBot chatBot = new DiscordChatBot(client, provider, new DiscordChatBotDispatcher());
        chatBot.onCreated();
        chatBot.registerSensor();
        chatBot.onDestroy();
        return chatBot;
    }
}
