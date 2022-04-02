package chatbot.client;

import chatbot.client.common.chatbot.DiscordChatBotFactory;
import chatbot.client.common.command.CommandBuilder;
import chatbot.client.controller.ChatBotController;
import chatbot.client.service.LostArkAuctionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public ChatBotController chatBotClient(){
        return new ChatBotController.Builder(discordChatBotFactory(), commandFactory())
                .build();
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
                .addCommand("입찰",new LostArkAuctionService())
                .build();
    }
}
