package chatbot.client.common.command;

import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Command {
    public String startCommand;
    public String description;
    public String response;
    public List<String> options = new ArrayList<>();
}
