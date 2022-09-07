package chatbot.client.utils;

import chatbot.client.core.chat.Chat;
import chatbot.client.core.chat.ChatResult;
import chatbot.client.core.command.Command;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatUtils {
    public static String parseCommand(String message, Command command){
        String commandWithSpace = new StringBuilder(command.getStartCommand()).append(" ").toString();
        String result = message.replaceFirst(commandWithSpace, "");
        return result;
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
