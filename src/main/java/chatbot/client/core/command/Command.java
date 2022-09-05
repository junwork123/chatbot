package chatbot.client.core.command;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public enum Command {
    JOIN("!join", "음성채팅 입장", "i joined a channel"),
    OUT("!out", "음성채팅 퇴장", "i'm out"),
    PING("!ping", "기본 명령어", "pong!"),
    AUCTION("!입찰", "로아 경매 계산", "로아 경매 금액입니다.");
    private final String startCommand;
    private final String description;
    private final String displayMessage;
    private String prefix = "!";

    public String getStartCommand() {
        return new StringBuilder()
//                .append(prefix)
                .append(startCommand)
                .toString();
    }

    public String getDescription() {
        return description;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public String getPrefix() {
        return prefix;
    }
}
