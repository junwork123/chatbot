package chatbot.client.command;

import chatbot.client.controller.ChatBotController;
import chatbot.client.domain.DiscordMessageTemplate;
import chatbot.client.domain.MessageTemplateFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public class Command{
    public final ChatBotController controller;
    public final CommandVO vo;
    public String execute(String message){
        String content = parseMessageWithCommand(message, vo.getStartCommand());
        log.info("템플릿 양식 {} : {}", vo.getPlatformName(), vo.getTemplateName());
        MessageTemplateFactory.findTemplate(vo.getPlatformName(), vo.getTemplateName());
        return controller.response(DiscordMessageTemplate.TEXT, content).getResponse().getMessage();
    }

    private String parseMessageWithCommand(String message, String command){
        String commandWithSpace = new StringBuilder(command).append(" ").toString();
        String result = message.replaceFirst(commandWithSpace, "");
        return result;
    }
}
