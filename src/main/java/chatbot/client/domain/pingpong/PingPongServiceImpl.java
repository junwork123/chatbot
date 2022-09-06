package chatbot.client.domain.pingpong;

import chatbot.client.core.chat.Chat;
import chatbot.client.core.chat.ChatResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PingPongServiceImpl implements PingPongService {
    public ChatResult getPingpong(){
        Chat chat = Chat.builder()
                .content("pong")
                .build();

        return ChatResult.builder()
                .chat(chat)
                .build();
    }
}

