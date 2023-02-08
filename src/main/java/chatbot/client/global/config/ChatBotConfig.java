package chatbot.client.global.config;

import chatbot.client.global.core.ChatBot;
import chatbot.client.global.core.ChatBotFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ChatBotConfig {
    @Bean
    public ChatBot chatBot(ChatBotFactory factory){
        return factory.CreateChatBot();
    }
}
