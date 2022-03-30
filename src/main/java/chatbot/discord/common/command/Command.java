package chatbot.discord.common.command;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface Command {
    public String getName();
    public Mono<Void> execute(ChatInputInteractionEvent event);
}
