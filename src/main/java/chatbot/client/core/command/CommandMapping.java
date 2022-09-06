package chatbot.client.core.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommandMapping {
    String startCommand() default "hello";
    String description() default "";
    String displayMessage() default "";
    String prefix() default "!";
}
