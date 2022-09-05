package chatbot.client.core.request;

import chatbot.client.core.command.Command;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

    @Override
    public String toString() {
        return "messenger=" + this.messenger + "\n" +
            "messageNo=" + this.messageNo + "\n" +
            "user=" + this.user + "\n" +
            "room=" + this.room + "\n" +
            "thread=" + this.thread + "\n" +
            "content=" + this.content + "\n" +
            "command=" + this.command + "\n";
    }
}
