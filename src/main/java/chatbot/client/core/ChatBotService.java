package chatbot.client.core;

import chatbot.client.message.MessageTemplate;
import lombok.NonNull;

import java.util.Optional;

public interface ChatBotService {
    public Optional<MessageTemplate> makeResponse(@NonNull MessageTemplate template, @NonNull String content);
}
