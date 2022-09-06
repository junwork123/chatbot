package chatbot.client.core.result;

public abstract class SimpleMessageChatResult implements ChatResult {
    protected String message;

    public String getMessage() {
        return message;
    }

    public void comment(String comment) {
        message += comment;
    }
}
