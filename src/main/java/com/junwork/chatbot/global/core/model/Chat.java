package com.junwork.chatbot.global.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chat {
    private String messenger;
    private String messageNo;
    private String user;
    private String room;
    private String thread;
    private String content;

    @Builder
    public Chat(String messenger, String messageNo, String user, String room, String thread, String content) {
        this.messenger = messenger;
        this.messageNo = messageNo;
        this.user = user;
        this.room = room;
        this.thread = thread;
        this.content = content;
    }
}
