package chatbot.client.domain.pingpong;

import chatbot.client.core.ChatBotService;
import chatbot.client.core.message.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class PingPongService implements ChatBotService {
    @Override
    public Optional<MessageTemplate> makeResponse(MessageTemplate template, String content) {
        log.info("content = {}", content);
        template.convert("Pong!");
        return ofNullable(template.isEmpty() ? null : template);
    }
}
