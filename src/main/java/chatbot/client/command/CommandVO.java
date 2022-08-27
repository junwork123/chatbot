package chatbot.client.command;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandVO {
    private String startCommand;
    private String description;
    private String response;
    private String platformName;
    private String templateName;
}
