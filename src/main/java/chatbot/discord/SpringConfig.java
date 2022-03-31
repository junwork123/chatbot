package chatbot.discord;

import chatbot.discord.common.chatbot.ChatBotClient;
import chatbot.discord.common.chatbot.DiscordChatBotFactory;
import chatbot.discord.common.command.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SpringConfig {
    @Bean
    public ChatBotClient chatBotClient(){
        return new ChatBotClient.Builder(discordChatBotFactory(), commandFactory())
                //.addCommand(new GreetCommand())
                .build();
    }
    @Bean
    public DiscordChatBotFactory discordChatBotFactory(){
        return new DiscordChatBotFactory();
    }

    @Bean
    public CommandFactory commandFactory() {
        return new CommandFactory.Builder()
                .addCommand("book")
                .addCommand("greet")
                .addCommand("ping")
                .build();
    }
}
