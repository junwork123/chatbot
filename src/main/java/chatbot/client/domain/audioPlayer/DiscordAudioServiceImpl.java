package chatbot.client.domain.audioPlayer;

import chatbot.client.core.command.Command;
import chatbot.client.core.chat.Chat;
import chatbot.client.core.chat.ChatResult;
import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.PartialMember;
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
}
