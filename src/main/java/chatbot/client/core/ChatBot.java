package chatbot.client.core;

import chatbot.client.action.Action;
import chatbot.client.message.MessageTemplate;

import java.util.List;

public interface ChatBot {
    public void onCreated();
    public void registerActions(List<Action> actions);
    public void registerMessageTemplates(List<MessageTemplate> templates);
    public void onDestroy();

}
