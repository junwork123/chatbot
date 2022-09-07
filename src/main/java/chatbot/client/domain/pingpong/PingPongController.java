package chatbot.client.domain.pingpong;

import chatbot.client.core.ChatBotController;
import chatbot.client.core.chat.Chat;
import chatbot.client.core.command.Command;
import chatbot.client.core.command.CommandMapping;
import chatbot.client.core.chat.ChatRequest;
import chatbot.client.core.chat.ChatResult;
import chatbot.client.utils.ChatUtils;
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
