package chatbot.client.core;

import chatbot.client.action.Action;

import javax.annotation.PostConstruct;
import java.util.List;

public interface ChatBot {
    public void onCreated();
    public void registerActions(List<Action> actions);
    public void onDestroy();
}
