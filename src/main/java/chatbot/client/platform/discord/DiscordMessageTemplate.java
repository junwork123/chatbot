package chatbot.client.platform.discord;

import chatbot.client.message.MessageTemplate;
import lombok.Getter;
import java.util.List;

@Getter
public enum DiscordMessageTemplate implements MessageTemplate {
    /*
     @Naming Rule : 플랫폼명_템플릿명("플랫폼명 템플릿명", "템플릿 내용");
     */
    TEXT("TEXT", "템플릿 내용");

    private String platform = "DISCORD";
    private String templateName;
    private String message;
    private String template;

    DiscordMessageTemplate(String templateName, String template){
        this.templateName = templateName;
        this.template = template;
    }
    @Override
    public void convert(String message) {
        // 템플릿에 메시지 삽입
        this.message = message;
    }

    @Override
    public void convert(List<String> message) {
        //@Todo : 메시지 템플릿 만들기
        return;
    }

    @Override
    public boolean isEmpty() {
        return this.message.isEmpty();
    }
}
