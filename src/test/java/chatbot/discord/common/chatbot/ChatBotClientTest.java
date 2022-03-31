package chatbot.discord.common.chatbot;

import discord4j.core.GatewayDiscordClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChatBotClientTest {
    ChatBotClient botClient;

    @Test
    public void 디스코드_챗봇팩토리_클래스확인() throws IllegalAccessException {
        //given
        botClient = new ChatBotClient.Builder(new DiscordChatBotFactory())
                .build();

        //when
        DiscordChatBotAdapter adapter = new DiscordChatBotAdapter();

        //then
        Assertions.assertThat(adapter.supports(botClient.getChatBotFactory())).isEqualTo(true);
    }

}