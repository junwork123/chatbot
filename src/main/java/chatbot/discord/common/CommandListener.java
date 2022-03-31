package chatbot.discord.common;

import chatbot.discord.common.command.Command;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

public class CommandListener {
//    @Autowired
//    private final Collection<Command> commands;
//
//    public CommandListener(List<Command> slashCommands) {
//        commands = slashCommands;
//
//
//        client.on(ChatInputInteractionEvent.class, this::handle).subscribe();
//    }
//
//
//    public Mono<Void> handle(ChatInputInteractionEvent event) {
//        //Convert our list to a flux that we can iterate through
//        return Flux.fromIterable(commands)
//                //Filter out all commands that don't match the name this event is for
//                .filter(command -> command.getName().equals(event.getCommandName()))
//                //Get the first (and only) item in the flux that matches our filter
//                .next()
//                //Have our command class handle all logic related to its specific command.
//                .flatMap(command -> command.execute(event));
//    }

}
