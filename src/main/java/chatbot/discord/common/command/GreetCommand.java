package chatbot.discord.common.command;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class GreetCommand implements Command{
    private final Map<String, String> entry = new HashMap<>();

    @Override
    public String getStartCommand() {
        return null;
    }

    @Override
    public boolean isExecutable(String command) {
        return false;
    }

    @Override
    public Mono<Void> execute(String command) {
        return null;
    }
//    @Override
//    public String getName() {
//        return "greet";
//    }
//
//    @Override
//    public Mono<Void> execute(ChatInputInteractionEvent event) {
//        String name = event.getOption("name")
//                .flatMap(ApplicationCommandInteractionOption::getValue)
//                .map(ApplicationCommandInteractionOptionValue::asString)
//                .get(); //This is warning us that we didn't check if its present, we can ignore this on required options
//
//        //Reply to the slash command, with the name the user supplied
//        return  event.reply()
//                .withEphemeral(true)
//                .withContent("Hello, " + name);
//    }
}
