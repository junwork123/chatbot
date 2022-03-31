package chatbot.discord.common.command;

import reactor.core.publisher.Mono;

public interface Command {
    public String getStartCommand();
    public boolean isExecutable(String command);
    public Mono<Void> execute(String command);
}
