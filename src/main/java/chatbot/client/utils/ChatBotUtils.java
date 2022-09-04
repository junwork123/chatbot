package chatbot.client.utils;

import chatbot.client.core.Answerable;
import chatbot.client.core.ChatBotController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class ChatBotUtils {
    public static Class<?> findController(String command){
        Class<?>[] classes = ChatBotController.class.getDeclaredClasses();
        for(Class<?> item : classes){
            String itemCommand = item.getAnnotation(ChatBotController.class).command();
            if(itemCommand.equals(command))
                return item;
        }
        return null;
    }
    public static Method findControllerMapping(String command){
        Class<?> controller = findController(command);
        Method[] methods = controller.getDeclaredMethods();
        for(Method item : methods){
            String itemCommand = item.getAnnotation(ChatBotController.class).command();
            if(itemCommand.equals(command))
                return item;
        }
        return null;
    }
}
