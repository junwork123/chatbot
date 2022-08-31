package chatbot.client.platform.discord.actions;

import chatbot.client.action.Action;
import chatbot.client.command.Command;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class DiscordMessageAction implements Action{

    private DiscordMessageAction(){}
    public static class Builder{
        Flux<Message> messageFlux;

        public Builder(){}

        public Flux<Message> Build(){
            return messageFlux;
        }

        public Builder getMessageFromClient(GatewayDiscordClient client){
            messageFlux = client.getEventDispatcher().on(MessageCreateEvent.class)
                    .map(MessageCreateEvent::getMessage);
            return this;
        }

        public Builder filterCommand(String startCommand){
            messageFlux = messageFlux
                    .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                    .filter(message -> message.getContent().startsWith(startCommand));
            return this;
        }

        public Builder createMessage(String content){
            messageFlux = messageFlux.map(message -> {
                message.getChannel().
                        flatMap(channel -> channel.createMessage(content))
                        .subscribe();
                return message;
            });
            return this;
        }

        public Builder executeCommand(Command command){
            messageFlux = messageFlux.map(message -> {
                String response = command.execute(message.getContent());
                message.getChannel().flatMap(channel -> channel.createMessage(response))
                        .subscribe();
                return message;
            });
            return this;
        }
    }
}
