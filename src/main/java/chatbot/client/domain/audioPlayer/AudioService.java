package chatbot.client.domain.audioPlayer;

import chatbot.client.core.result.DefaultChatResult;
import discord4j.core.object.entity.Message;
import discord4j.voice.AudioProvider;
import reactor.core.publisher.Flux;

public interface AudioService {
    public DefaultChatResult joinVoiceChannel(Flux<Message> messageFlux, AudioProvider provider);
}
