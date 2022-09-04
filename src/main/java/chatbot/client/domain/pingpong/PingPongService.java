package chatbot.client.domain.pingpong;

import chatbot.client.core.message.MessageTemplate;
import lombok.NonNull;

import java.util.Optional;

public interface PingPongService {
    public Optional<MessageTemplate> makeResponse(@NonNull MessageTemplate template, @NonNull String content);
}
