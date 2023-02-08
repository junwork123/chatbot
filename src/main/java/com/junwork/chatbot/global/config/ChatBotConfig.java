package com.junwork.chatbot.global.config;

import com.junwork.chatbot.global.core.ChatBot;
import com.junwork.chatbot.global.core.ChatBotFactory;
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
