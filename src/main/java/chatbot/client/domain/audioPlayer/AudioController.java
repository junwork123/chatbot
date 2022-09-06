package chatbot.client.domain.audioPlayer;

import chatbot.client.core.ChatBotController;
import chatbot.client.core.command.CommandMapping;
import chatbot.client.core.result.DefaultChatResult;
import discord4j.core.object.entity.Message;
import discord4j.voice.AudioProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import reactor.core.publisher.Flux;

import java.util.concurrent.ConcurrentHashMap;


@RequiredArgsConstructor
@ChatBotController
public class AudioController{
    private final AudioService service;

    @CommandMapping(startCommand = "join")
    public DefaultChatResult joinVoiceChannel(Model model){
        Flux<Message> messageFlux = (Flux<Message>) model.getAttribute("messageFlux");
        AudioProvider provider = (AudioProvider) model.getAttribute("provider");
        return service.joinVoiceChannel(messageFlux, provider);
    }
}
