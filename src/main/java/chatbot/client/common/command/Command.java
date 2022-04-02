package chatbot.client.common.command;

import chatbot.client.service.ChatBotService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Command{
    public final ChatBotService service;
    public final CommandVO vo;
    public String execute(String message){
        String content = parseMessageWithCommand(message, vo.getStartCommand());
        String optionParam = null;
        for (String option : vo.getOptions()) {
            if(content.startsWith(option)){
                optionParam = option;
                content = parseMessageWithCommand(message, option);
            }
        }
        return service.doService(optionParam, content);
    }

    private String parseMessageWithCommand(String message, String command){
        String commandWithSpace = new StringBuilder(command).append(" ").toString();
        String result = message.replaceFirst(commandWithSpace, "");
        return result;
    }
}
