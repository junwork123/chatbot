package com.junwork.chatbot.global.util;

import com.junwork.chatbot.global.core.ChatBotController;
import com.junwork.chatbot.global.core.command.Command;
import com.junwork.chatbot.global.core.command.CommandMapping;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BeanUtils {
    public static Object getBean(String beanName){
        ApplicationContext ac = ApplicationContextProvider.getApplicationContext();
        return ac.getBean(beanName);
    }

    public static Object getBean(Class<?> clazz){
        ApplicationContext ac = ApplicationContextProvider.getApplicationContext();
        return ac.getBean(clazz);
    }

    public static final String scanPackages = "chatbot.client.domain";
    private static Reflections findPackage(String packageName) {
        Reflections reflections = new Reflections(packageName, Scanners.TypesAnnotated);
        log.info("발견한 ChatBotController : " + reflections.getTypesAnnotatedWith(ChatBotController.class).size());
        return reflections;
    }
    public static Map<Class<?>, Method> findController(Command command) {
        Map<Class<?>, Method> resultMap = new HashMap<>();
        findPackage(scanPackages).getTypesAnnotatedWith(ChatBotController.class)
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
            log.info("찾은 메소드 : " + method.getName());

            if (method.isAnnotationPresent(CommandMapping.class)
                    && method.getAnnotation(CommandMapping.class).startCommand().equals(command.getStartCommand())) {
                result = method;
                log.info("일치하는 메소드 명 : " + result.getName());
            }
        }
        return result;
    }
}
