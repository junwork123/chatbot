package chatbot.client.domain.audioPlayer;

import chatbot.client.core.ChatBotService;
import chatbot.client.message.MessageTemplate;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AudioService implements ChatBotService {
    @Override
    public Optional<MessageTemplate> makeResponse(@NonNull MessageTemplate template, @NonNull String content) {
        return Optional.empty();
    }
}
