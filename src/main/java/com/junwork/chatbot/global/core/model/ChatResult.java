package com.junwork.chatbot.global.core.model;

import lombok.Builder;

public record ChatResult (Chat chat) {
    @Builder
    public ChatResult {
    }
}
