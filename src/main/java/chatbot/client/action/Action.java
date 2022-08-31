package chatbot.client.action;

import chatbot.client.core.ChatBotController;
import chatbot.client.platform.discord.DiscordMessageTemplate;
import chatbot.client.message.MessageTemplateFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public class Action {
    public final ChatBotController controller;
    public final Command command;
    public String execute(String message){
        String content = parseMessageWithCommand(message, command.getStartCommand());
        log.info("템플릿 양식 {} : {}", command.getPlatformName(), command.getTemplateName());
        MessageTemplateFactory.findTemplate(command.getPlatformName(), command.getTemplateName());
        return controller.response(DiscordMessageTemplate.TEXT, content).getResponse().getMessage();
    }

    private String parseMessageWithCommand(String message, String command){
        String commandWithSpace = new StringBuilder(command).append(" ").toString();
        String result = message.replaceFirst(commandWithSpace, "");
        return result;
    }
}