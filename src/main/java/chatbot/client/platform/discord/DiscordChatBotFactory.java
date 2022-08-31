package chatbot.client.platform.discord;

import chatbot.client.action.Action;
import chatbot.client.core.ChatBot;
import chatbot.client.core.ChatBotFactory;
import chatbot.client.platform.discord.audioProvider.LavaPlayerAudioProvider;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.voice.AudioProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

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
public class DiscordChatBotFactory implements ChatBotFactory {
    @Value("${DISCORD_TOKEN_ID}")
    private String DISCORD_TOKEN_ID;

    @Override
    public ChatBot CreateChatBot(List<Action> actions) {
        // Discord 챗봇 생성
        GatewayDiscordClient client = DiscordClientBuilder.create(DISCORD_TOKEN_ID).build().login().block();
        AudioProvider provider = LavaPlayerAudioProvider.createAudioProvider();
        DiscordChatBot chatBot = new DiscordChatBot(client, provider);

        // Discord 챗봇 초기설정
        DiscordActionFactory.init(chatBot, actions);
        return chatBot;
    }
}
