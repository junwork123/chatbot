package chatbot.client.action;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Command {
    private String startCommand;
    private String description;
    private String displayMessage;
    private String platformName;
    private String templateName;
}
