package chatbot.client.core.action;

import chatbot.client.core.Answerable;
import chatbot.client.core.message.MessageTemplate;
import chatbot.client.platform.discord.DiscordChatBot;
import chatbot.client.platform.discord.DiscordMessageTemplate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public class Action {
    public final Answerable controller;
    public final Command command;
    public String execute(String message){
        String content = parseMessageWithCommand(message, command.getStartCommand());
        log.info("템플릿 양식 {} : {}", command.getPlatformName(), command.getTemplateName());
        MessageTemplate.findTemplate(DiscordChatBot.templates, command);
        return controller.response(DiscordMessageTemplate.TEXT, content).getResponse().getMessage();
    }

    private String parseMessageWithCommand(String message, String command){
        String commandWithSpace = new StringBuilder(command).append(" ").toString();
        String result = message.replaceFirst(commandWithSpace, "");
        return result;
    }
}
