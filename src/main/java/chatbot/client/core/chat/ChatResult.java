package chatbot.client.core.chat;

import chatbot.client.core.chat.Chat;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatResult {
    private final Chat chat;

    @Builder
    public ChatResult(Chat chat) {
        this.chat = chat;
    }
}
