package chatbot.client.잡동사니;
//
//public class CommandListener {
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
//
//}
