package chatbot.discord.common.command;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class BookingCommand implements Command{
    private final Map<String, String> entry = new HashMap<>();

    @Override
    public String getName() {
        return "booking";
    }

    @Override
    public Mono<Void> execute(ChatInputInteractionEvent event) {
        return event.reply()
                .withEphemeral(true)
                .withContent("레이드 가실래요?");
    }

}
