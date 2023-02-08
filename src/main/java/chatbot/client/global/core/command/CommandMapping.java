package chatbot.client.global.core.command;

import chatbot.client.global.util.ChatUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommandMapping {
    String prefix() default ChatUtils.prefix;
    String startCommand() default "hello";
    String description() default "";
    String displayMessage() default "";
}
