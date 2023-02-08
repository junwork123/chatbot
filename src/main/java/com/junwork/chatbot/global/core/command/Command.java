package com.junwork.chatbot.global.core.command;

import com.junwork.chatbot.global.util.ChatUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum Command {
    JOIN("join", "음성채팅 입장", "i joined a channel", true),
    OUT("out", "음성채팅 퇴장", "i'm out", true),
    PING("ping", "기본 명령어", "기본 커맨드", false),
    AUCTION("입찰", "로아 경매 계산", "로아 경매 금액입니다.", false);
    private final String startCommand;
    private final String description;
    private final String displayMessage;
    private final boolean isAction;
    private final String prefix = ChatUtils.prefix;

    public String getStartCommandWithPrefix() {
        return prefix + startCommand;
    }
}
