package chatbot.client;

import chatbot.client.command.Command;
import chatbot.client.command.CommandBuilder;
import chatbot.client.domain.ChatBot;
import chatbot.client.domain.ChatBotFactory;
import chatbot.client.domain.DiscordChatBotFactory;
import chatbot.client.service.LostArkAuctionService;
import chatbot.client.service.PingPongService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringConfig {

    @Bean
    public ChatBot chatBot(){
        ChatBotFactory chatBotFactory = discordChatBotFactory();
        CommandBuilder commandBuilder = commandFactory();

        List<Command> commands = commandBuilder.getCommands();
        ChatBot createdChatBot = chatBotFactory.CreateChatBot(commands);
        return createdChatBot;
    }
    @Bean
    public DiscordChatBotFactory discordChatBotFactory(){
        return new DiscordChatBotFactory();
    }

    @Bean
    public CommandBuilder commandFactory() {
        return new CommandBuilder.Builder()
                .addCommand("ping", new PingPongService())
                .addCommand("auction",new LostArkAuctionService())
                .build();
    }
}
