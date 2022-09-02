package chatbot.client;

import chatbot.client.action.Action;
import chatbot.client.action.ActionBuilder;
import chatbot.client.core.ChatBot;
import chatbot.client.core.ChatBotFactory;
import chatbot.client.domain.audioPlayer.AudioController;
import chatbot.client.domain.lostArkAuction.LostArkAuctionController;
import chatbot.client.domain.pingpong.PingPongController;
import chatbot.client.platform.discord.DiscordChatBotFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringConfig {
    @Bean
    public ChatBot chatBot(){
        ChatBotFactory chatBotFactory = discordChatBotFactory();
        ActionBuilder actionBuilder = commandFactory();

        List<Action> actions = actionBuilder.getActions();
        ChatBot chatBot = chatBotFactory.CreateChatBot(actions);
        return chatBot;
    }
    @Bean
    public DiscordChatBotFactory discordChatBotFactory(){
        return new DiscordChatBotFactory();
    }

    @Bean
    public ActionBuilder commandFactory() {
        return new ActionBuilder.Builder()
                .addCommand("ping", new PingPongController())
                .addCommand("auction",new LostArkAuctionController())
                .addCommand("play",new AudioController())
                .build();
    }
}
