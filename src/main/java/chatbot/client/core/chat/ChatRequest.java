package chatbot.client.core.chat;

import chatbot.client.core.command.Command;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.ui.Model;

@Getter
@Setter
public class ChatRequest {

    private Chat chat;
    private Command command;
    private Model model;

    public ChatRequest(){}
    @Builder
    public ChatRequest(Chat chat, Command command, Model model) {
        this.chat = chat;
        this.command = command;
        this.model = model;
    }
}
