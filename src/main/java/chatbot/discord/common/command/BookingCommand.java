package chatbot.discord.common.command;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class BookingCommand implements Command{
    private final String StartCommand;

    public BookingCommand(String startCommand) {
        StartCommand = startCommand;
    }

    private final Map<String, String> entry = new HashMap<>();

    @Override
    public String getStartCommand() {
        return ;
    }

    @Override
    public boolean isExecutable(String command) {
        return false;
    }

    @Override
    public Mono<Void> execute(String command) {
        return null;
    }
}
