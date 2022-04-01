package chatbot.client.common.command;

import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;

import java.util.List;

@Getter @Setter
public class Command {
    /**
     * {
     *   "startCommand": "!예약",
     *   "description": "날짜예약 커맨드",
     *   "options": []
     * }
     */
    public String startCommand;
    public String description;
    public String response;
    public List<String> options;

    public boolean isExecutable(String command) {
        return false;
    }

    public Mono<Void> execute(String command) {
        return null;
    }

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
