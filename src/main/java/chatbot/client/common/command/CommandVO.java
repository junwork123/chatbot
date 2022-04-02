package chatbot.client.common.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandVO {
    public String startCommand;
    public String description;
    public String response;
    public List<String> options = new ArrayList<>();
}
