package chatbot.client.service;

import lombok.NonNull;
import org.springframework.lang.Nullable;

public interface ChatBotService {
    public String makeResponse(@Nullable String option, @NonNull String content);
}
