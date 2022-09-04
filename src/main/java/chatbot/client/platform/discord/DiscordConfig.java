package chatbot.client.platform.discord;

import chatbot.client.core.Responsible;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordConfig {
    @Bean
    @ConditionalOnMissingBean
    public DiscordDispatcher discordDispatcher(Responsible controller) {
        return new DiscordDispatcher(controller);
    }

    @Bean
    @ConditionalOnMissingBean
    public DiscordEventSensor discordEventSensor(DiscordDispatcher dispatcher) {
        return new DiscordEventSensor(dispatcher);
    }

}
