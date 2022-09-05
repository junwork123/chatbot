package chatbot.client.utils;

import org.springframework.context.ApplicationContext;

public class BeanUtils {
    public static Object getBean(String beanName){
        ApplicationContext ac = ApplicationContextProvider.getApplicationContext();
        return ac.getBean(beanName);
    }

    public static Object getBean(Class<?> clazz){
        ApplicationContext ac = ApplicationContextProvider.getApplicationContext();
        return ac.getBean(clazz);
    }
}
