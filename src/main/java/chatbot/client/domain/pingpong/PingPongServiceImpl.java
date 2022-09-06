package chatbot.client.domain.pingpong;

import chatbot.client.core.result.DefaultChatResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PingPongServiceImpl implements PingPongService {
    public DefaultChatResult getPingpong(){
        return new DefaultChatResult.Builder()
                .message("pong")
                .build();
    }
}

