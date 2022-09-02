package chatbot.client.message;

import chatbot.client.action.Command;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public interface MessageTemplate {
    public static MessageTemplate findTemplate(List<MessageTemplate> templates, Command command) {
        checkNotNull(templates, "템플릿이 제공되지 않았습니다");
        checkNotNull(command, "커맨드가 올바르지 않습니다");
        System.out.println("템플릿 리스트 크기 : " + templates.size());

        return templates.stream()
                .filter(t -> command.getPlatformName().equals(t.getPlatformName()))
                .filter(t -> command.getTemplateName().equals(t.getTemplateName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 인수가 전달되었습니다."));
    }

    public String getPlatformName();
    public String getTemplateName();
    public String getMessage();
    public void convert(String message);
    public void convert(List<String> message);
    public boolean isEmpty();
}
