package chatbot.client.common.chatbot;

public interface ChatBot {
    public boolean hasCommands();
    public void publish();
    public String response(String command);
}
