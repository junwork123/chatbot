package chatbot.client.core;

import chatbot.client.core.action.Action;
import chatbot.client.core.message.MessageTemplate;

import java.util.List;

public interface ChatBot {
    public void onCreated();
    public void registerActions(List<Action> actions);
    public void registerMessageTemplates(List<MessageTemplate> templates);
    public void onDestroy();

}
