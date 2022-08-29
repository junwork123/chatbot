package chatbot.client;

import chatbot.client.command.Command;
import chatbot.client.command.CommandBuilder;
import chatbot.client.controller.LostArkAuctionController;
import chatbot.client.controller.PingPongController;
import chatbot.client.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringConfig {
    @Bean
    public void messageTemplateFactory(){
        MessageTemplateFactory.init();
    }

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
                .addCommand("ping", new PingPongController())
                .addCommand("auction",new LostArkAuctionController())
                .build();
    }
}
