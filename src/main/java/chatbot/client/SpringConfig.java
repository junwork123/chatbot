package chatbot.client;

import chatbot.client.common.chatbot.ChatBot;
import chatbot.client.common.chatbot.ChatBotFactory;
import chatbot.client.common.chatbot.DiscordChatBotFactory;
import chatbot.client.common.command.Command;
import chatbot.client.common.command.CommandBuilder;
import chatbot.client.controller.ChatBotController;
import chatbot.client.service.LostArkAuctionService;
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
//                .addCommand("ping")
//                .addCommand("로붕쿤")
//                .addCommand("예약")
                .addCommand("auction",new LostArkAuctionService())
                .build();
    }
}
