package com.junwork.chatbot.domain.pingpong;

import com.junwork.chatbot.global.core.ChatBotController;
import com.junwork.chatbot.global.core.command.CommandMapping;
import com.junwork.chatbot.global.core.model.ChatRequest;
import com.junwork.chatbot.global.core.model.ChatResult;
import com.junwork.chatbot.global.util.ChatUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ChatBotController
public class PingPongController{
    private final PingPongService service;

    @CommandMapping(startCommand = "ping")
    public ChatResult pingpong(ChatRequest request){
        String content = service.getPingpong();
        return ChatUtils.createSimpleChatResult(content);
    }
}
