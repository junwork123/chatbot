package chatbot.client.domain.audioPlayer;

import chatbot.client.core.command.Command;
import chatbot.client.core.chat.Chat;
import chatbot.client.core.chat.ChatResult;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.VoiceChannel;
import discord4j.core.spec.VoiceChannelJoinSpec;
import discord4j.voice.AudioProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@RequiredArgsConstructor
@Service
public class DiscordAudioServiceImpl implements AudioService {
    public Flux<VoiceChannel> setVoiceState (Flux<Message> messageFlux){
        return messageFlux.flatMap(message -> message.getAuthorAsMember())
                .flatMap(member -> member.getVoiceState())
                .flatMap(voiceState -> voiceState.getChannel());
    }
    @Override
    public ChatResult joinVoiceChannel(Flux<Message> messageFlux, AudioProvider provider) {
        Flux<VoiceChannel> voiceChannelFlux = setVoiceState(messageFlux);
        voiceChannelFlux.map(channel -> {
            channel.join(VoiceChannelJoinSpec.builder()
                    .provider(provider)
                    .build()).subscribe();
            return channel;
        }).subscribe();

        Chat chat = Chat.builder()
                .content(Command.JOIN.getDisplayMessage())
                .build();

        return ChatResult.builder()
                .chat(chat)
                .build();

    }
}
