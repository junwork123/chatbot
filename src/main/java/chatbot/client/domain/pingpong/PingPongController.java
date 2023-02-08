package chatbot.client.domain.pingpong;

import chatbot.client.global.core.ChatBotController;
import chatbot.client.global.core.command.CommandMapping;
import chatbot.client.global.core.model.ChatRequest;
import chatbot.client.global.core.model.ChatResult;
import chatbot.client.global.util.ChatUtils;
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
