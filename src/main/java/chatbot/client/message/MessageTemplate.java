package chatbot.client.message;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public interface MessageTemplate {
    public String getPlatform();
    public String getTemplateName();
    public String getMessage();
    public void convert(String message);
    public void convert(List<String> message);
    public boolean isEmpty();
}
