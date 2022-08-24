package chatbot.client.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

public interface ChatBotService {
    public String doService(@Nullable String option, @NonNull String content);
}
