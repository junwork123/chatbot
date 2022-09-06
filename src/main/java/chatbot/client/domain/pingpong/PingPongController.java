package chatbot.client.domain.pingpong;

import chatbot.client.core.ChatBotController;
import chatbot.client.core.command.CommandMapping;
import chatbot.client.core.result.DefaultChatResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ChatBotController
public class PingPongController{
    private final PingPongService service;

    @CommandMapping(startCommand = "ping")
    public DefaultChatResult pingpong(String content){
        return service.getPingpong();
    }
}
