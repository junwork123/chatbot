package chatbot.client.platform.discord.actions;

import chatbot.client.action.ActionKind;
import chatbot.client.action.Action;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Getter
public class DiscordMessageAction {
    private DiscordMessageAction(){}
    public static Flux<Message> registerCommand(GatewayDiscordClient client, ActionKind action){
        return new Builder()
                .getMessageFromClient(client)
                .filterCommand(action.getStartCommand())
                .createMessage(action.getDisplayMessage())
                .Build();
    }
    public static Flux<Message> registerCommand(GatewayDiscordClient client, Action action){
        return new Builder()
                .getMessageFromClient(client)
                .filterCommand(action.getCommand().getStartCommand())
                .executeCommand(action)
                .Build();
    }

    public static class Builder{
        Flux<Message> messageFlux;
        String command;
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
            this.command = startCommand;
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

        public Builder executeCommand(Action action){
            messageFlux = messageFlux.map(message -> {
                String response = action.execute(message.getContent());
                message.getChannel().flatMap(channel -> channel.createMessage(response))
                        .subscribe();
                return message;
            });
            return this;
        }
    }
}
