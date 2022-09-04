package chatbot.client.domain.audioPlayer;

import chatbot.client.core.ChatBot;
import chatbot.client.core.ChatBotService;
import chatbot.client.core.message.MessageTemplate;
import chatbot.client.platform.discord.DiscordChatBot;
import chatbot.client.platform.discord.audio.LavaPlayerAudioProvider;
import chatbot.client.platform.discord.audio.TrackScheduler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Optional.ofNullable;
@Slf4j
@RequiredArgsConstructor
@Service
public class AudioService implements ChatBotService {
    private final ChatBot chatBot;

    @Override
    public Optional<MessageTemplate> makeResponse(@NonNull MessageTemplate template, @NonNull String content) {
        log.info("content = {}", content);
        LavaPlayerAudioProvider provider = ((DiscordChatBot) chatBot).getProvider();
        TrackScheduler scheduler = provider.getScheduler();
        checkNotNull(provider, "provider가 지정되지 않았습니다");
        checkNotNull(scheduler, "scheduler가 지정되지 않았습니다");
        checkNotNull(content, "재생할 곡이 지정되지 않았습니다");
        provider.getPlayerManager().loadItem(content, scheduler);

        String responseBody = content;
        template.convert(responseBody);
        return ofNullable(template.isEmpty() ? null : template);
    }
}
