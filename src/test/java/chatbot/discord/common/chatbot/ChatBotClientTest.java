package chatbot.discord.common.chatbot;

import chatbot.discord.common.command.CommandFactory;
import discord4j.core.GatewayDiscordClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChatBotClientTest {
    @Autowired
    ChatBotClient botClient;

    @Autowired
    CommandFactory commandFactory;

    @Autowired
    DiscordChatBotFactory discordChatBotFactory;

    @Test
    public void 챗봇팩토리_클래스확인_디스코드() throws IllegalAccessException {
        //given
        botClient = new ChatBotClient.Builder(discordChatBotFactory, commandFactory)
                .build();

        //when
        DiscordChatBotAdapter adapter = new DiscordChatBotAdapter();
        GatewayDiscordClient createdClient = adapter.adapt(discordChatBotFactory);

        GatewayDiscordClient client = discordChatBotFactory.CreateChatBot();

        //then
        Assertions.assertThat(createdClient).isInstanceOf(client.getClass());
    }

}