package chatbot.client.action;

import chatbot.client.command.Command;
import lombok.Getter;

@Getter
public enum Action {
    JOIN("join", "음성채팅 입장"),
    OUT("out", "음성채팅 퇴장");
    private final String prefix = "!";
    private final String startCommand;
    private final String description;
    Action(String startCommand, String description) {
        this.startCommand = prefix + startCommand;
        this.description = description;
    }
}
