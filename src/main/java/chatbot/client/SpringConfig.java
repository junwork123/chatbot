package chatbot.client;

import chatbot.client.common.chatbot.ChatBotClient;
import chatbot.client.common.chatbot.DiscordChatBotFactory;
import chatbot.client.common.command.CommandFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
