package chatbot.client;

import chatbot.client.action.Action;
import chatbot.client.action.ActionBuilder;
import chatbot.client.core.ChatBot;
import chatbot.client.core.ChatBotFactory;
import chatbot.client.domain.audioPlayer.AudioController;
import chatbot.client.domain.audioPlayer.AudioService;
import chatbot.client.domain.lostArkAuction.LostArkAuctionController;
import chatbot.client.domain.lostArkAuction.LostArkAuctionService;
import chatbot.client.domain.pingpong.PingPongController;
import chatbot.client.domain.pingpong.PingPongService;
import chatbot.client.platform.discord.DiscordChatBot;
import chatbot.client.platform.discord.DiscordChatBotFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringConfig {
    @Bean
    @Autowired
    public ChatBot chatBot(ChatBotFactory factory){
        ActionBuilder actionBuilder = actionBuilder();

        List<Action> actions = actionBuilder.getActions();
        ChatBot chatBot = factory.CreateChatBot(actions);
        return chatBot;
    }

    @Bean
    public ActionBuilder actionBuilder() {
        return new ActionBuilder.Builder()
                .addCommand("ping", new PingPongController(new PingPongService()))
                .addCommand("auction",new LostArkAuctionController(new LostArkAuctionService()))
//                .addCommand("play",new AudioController(new AudioService()))
                .build();
    }
}
