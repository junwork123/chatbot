package chatbot.client.action;

import lombok.Getter;

@Getter
public enum ActionKind {
    JOIN("join", "음성채팅 입장", "i joined a channel"),
    OUT("out", "음성채팅 퇴장", "i'm out");
    private final String prefix = "!";
    private final String startCommand;
    private final String description;
    private final String displayMessage;

    ActionKind(String startCommand, String description, String displayMessage) {
        this.startCommand = prefix + startCommand;
        this.description = description;
        this.displayMessage = displayMessage;
    }
}
