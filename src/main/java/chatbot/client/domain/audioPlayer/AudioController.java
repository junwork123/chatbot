package chatbot.client.domain.audioPlayer;

import chatbot.client.core.ChatBotController;
import chatbot.client.core.command.CommandMapping;
import chatbot.client.core.chat.ChatRequest;
import chatbot.client.core.chat.ChatResult;
import discord4j.core.object.entity.Message;
import discord4j.voice.AudioProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import reactor.core.publisher.Flux;


@RequiredArgsConstructor
@ChatBotController
public class AudioController{
    private final AudioService service;

    @CommandMapping(startCommand = "join")
    public ChatResult joinVoiceChannel(ChatRequest request){
        Model model = request.getModel();
        Flux<Message> messageFlux = (Flux<Message>) model.getAttribute("messageFlux");
        AudioProvider provider = (AudioProvider) model.getAttribute("provider");
        return service.joinVoiceChannel(messageFlux, provider);
    }
}
