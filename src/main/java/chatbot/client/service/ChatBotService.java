package chatbot.client.service;

import chatbot.client.domain.MessageTemplate;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface ChatBotService {
    public Optional<MessageTemplate> makeResponse(@NonNull MessageTemplate template, @NonNull String content);
}
