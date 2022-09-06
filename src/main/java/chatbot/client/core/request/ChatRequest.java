package chatbot.client.core.request;

import chatbot.client.core.command.Command;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.ui.Model;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class ChatRequest {
    private final String messenger;
    private final String messageNo;
    private final String user;
    private final String room;
    private final String thread;
    private final String content;
    private final Command command;
    private final Model model;
}
