package chatbot.client;

import chatbot.client.core.action.Action;
import chatbot.client.core.action.ActionBuilder;
import chatbot.client.core.ChatBot;
import chatbot.client.core.ChatBotFactory;
import chatbot.client.core.event.EventQueue;
import chatbot.client.core.event.EventSensor;
import chatbot.client.core.event.TaskRunner;
import chatbot.client.domain.lostArkAuction.LostArkAuctionController;
import chatbot.client.domain.lostArkAuction.LostArkAuctionServiceImpl;
import chatbot.client.domain.pingpong.PingPongController;
import chatbot.client.domain.pingpong.PingPongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;

@Configuration
public class SpringConfig {
    @Bean
    @Autowired
    public ChatBot chatBot(ChatBotFactory factory){
        ActionBuilder actionBuilder = actionBuilder();
        List<Action> actions = actionBuilder.getActions();
        ChatBot chatBot = factory.CreateChatBot(actions);
        return chatBot;
    }
    @Bean
    public ActionBuilder actionBuilder() {
        return new ActionBuilder.Builder()
                .addCommand("ping", new PingPongController(new PingPongServiceImpl()))
                .addCommand("auction",new LostArkAuctionController(new LostArkAuctionServiceImpl()))
//                .addCommand("play",new AudioController(new AudioService()))
                .build();
    }
    @Bean
    @ConditionalOnMissingBean
    public EventQueue eventQueue(){
        return new EventQueue();
    }
    public static final String EVENT_QUEUE_TREAD_POOL = "eventQueueTreadPool";
    @Bean(name = EVENT_QUEUE_TREAD_POOL)
    public ThreadPoolTaskExecutor eventQueueTreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(1000);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
    @Bean
    @ConditionalOnMissingBean
    public TaskRunner taskRunner(
            @Qualifier(EVENT_QUEUE_TREAD_POOL) ThreadPoolTaskExecutor executor,
            EventQueue eventQueue,
            List<EventSensor> eventSensors
    ){
        return new TaskRunner(executor, eventQueue, eventSensors);
    }
}
