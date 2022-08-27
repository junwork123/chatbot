package chatbot.client.domain;

import lombok.Getter;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class MessageTemplateFactory {
    public static List<MessageTemplate> templates = new ArrayList<>();

    public static void init(){
        templates.addAll(List.of(DiscordMessageTemplate.values()));
    }

    public static MessageTemplate findTemplate(String platform, String templateName){
        checkNotNull(templates, "템플릿이 제공되지 않았습니다");
        checkNotNull(platform, "플랫폼 명이 올바르지 않습니다");
        checkNotNull(templateName, "템플릿 명이 올바르지 않습니다");
        System.out.println("템플릿 리스트 크기 : " + templates.size());

        return templates.stream()
                .filter(t -> platform.equals(t.getPlatform()))
                .filter(t -> templateName.equals(t.getTemplateName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 인수가 전달되었습니다."));
    };
}