package chatbot.client.utils;

import chatbot.client.core.ChatBotController;
import chatbot.client.core.command.Command;
import chatbot.client.core.command.CommandMapping;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ChatBotUtils {
    public static String parseCommand(String message, Command command){
        String commandWithSpace = new StringBuilder(command.getStartCommand()).append(" ").toString();
        String result = message.replaceFirst(commandWithSpace, "");
        return result;
    }

    public static final String scanPackages = "chatbot.client.domain";
    private static Reflections findPackage(String packageName) {
        Reflections reflections = new Reflections(packageName, Scanners.TypesAnnotated);
        log.info("reflections : " + reflections.getTypesAnnotatedWith(ChatBotController.class).size());
        return reflections;
    }
    public static Map<Class<?>, Method> findController(Command command) {
        Map<Class<?>, Method> resultMap = new HashMap<>();
        findPackage(scanPackages).getTypesAnnotatedWith(ChatBotController.class)
                .stream()
                .forEach(aClass -> {
                    Method method = findMethod(aClass, command);
                    if(method != null) {
                        resultMap.put(aClass, method);
                    }
                });
        return resultMap;
    }

    public static Method findMethod(Class<?> clazz, Command command){
        Method result = null;
        for (Method method : clazz.getDeclaredMethods()) {
            log.info("메소드 검색 : " + method.getName());

            if (method.isAnnotationPresent(CommandMapping.class)
                && method.getAnnotation(CommandMapping.class).startCommand().equals(command.getStartCommand())) {
                    result = method;
           }
        }
        return result;
    }
}
