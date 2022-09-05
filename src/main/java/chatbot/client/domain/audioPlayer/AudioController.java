package chatbot.client.domain.audioPlayer;

import chatbot.client.core.ChatBotController;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@ChatBotController
public class AudioController{
    private final AudioService service;


}
