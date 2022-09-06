package chatbot.client;

import chatbot.client.core.ChatBot;
import chatbot.client.core.ChatBotFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@Configuration
public class SpringConfig {
    @Bean
    public ChatBot chatBot(ChatBotFactory factory){
        ChatBot chatBot = factory.CreateChatBot();
        return chatBot;
    }
}
