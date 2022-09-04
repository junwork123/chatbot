package chatbot.client.domain.audioPlayer;

import chatbot.client.core.message.MessageTemplate;
import lombok.NonNull;

import java.util.Optional;

public interface AudioService {
    public Optional<MessageTemplate> makeResponse(@NonNull MessageTemplate template, @NonNull String content);
}
