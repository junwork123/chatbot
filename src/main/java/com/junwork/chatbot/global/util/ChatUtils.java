package com.junwork.chatbot.global.util;

import com.junwork.chatbot.global.core.model.Chat;
import com.junwork.chatbot.global.core.model.ChatResult;
import com.junwork.chatbot.global.core.command.Command;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatUtils {
    public static final String prefix = "!";
    public static String parseCommand(String message, Command command){
        String commandWithSpace = command.getStartCommand() + " ";
        return message.replaceFirst(commandWithSpace, "");
    }
    public static ChatResult createSimpleChatResult(String content) {
        Chat chat = Chat.builder()
                .content(content)
                .build();

        return ChatResult.builder()
                .chat(chat)
                .build();
    }
}
