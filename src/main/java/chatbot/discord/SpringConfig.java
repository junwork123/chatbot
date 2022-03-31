package chatbot.discord;

import chatbot.discord.common.chatbot.ChatBotClient;
import chatbot.discord.common.chatbot.ChatBotFactory;
import chatbot.discord.common.chatbot.DiscordChatBotFactory;
import chatbot.discord.common.command.BookingCommand;
import chatbot.discord.common.command.GreetCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public ChatBotClient chatBotClient(){
        return new ChatBotClient.Builder(new DiscordChatBotFactory())
                .addCommand(new GreetCommand())
                .addCommand(new BookingCommand())
                .build();
    }
}
