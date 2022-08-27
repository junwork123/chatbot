package chatbot.client.controller;

import chatbot.client.domain.ChatBot;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
@Controller
public interface ChatBotController {
    public String response(@Nullable String option, @NonNull String content);
}
