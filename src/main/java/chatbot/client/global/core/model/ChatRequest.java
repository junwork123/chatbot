package chatbot.client.global.core.model;

import chatbot.client.global.core.command.Command;
import lombok.Builder;
import org.springframework.ui.Model;

public record ChatRequest (Chat chat, Command command, Model model) {
    @Builder
    public ChatRequest {
    }
}
