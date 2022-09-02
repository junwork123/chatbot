package chatbot.client.platform.discord;

import chatbot.client.action.Command;
import chatbot.client.message.MessageTemplate;
import lombok.Getter;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public enum DiscordMessageTemplate implements MessageTemplate {
    TEXT("TEXT", "템플릿 내용");

    private String platformName = "DISCORD";
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
