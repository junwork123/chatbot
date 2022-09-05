package chatbot.client.platform.discord.EventSensor;

import chatbot.client.core.command.Command;
import chatbot.client.core.request.ChatRequest;
import chatbot.client.core.result.DefaultChatResult;
import chatbot.client.platform.discord.DiscordChatBot;
import chatbot.client.utils.ChatBotUtils;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Getter
@RequiredArgsConstructor
public class DiscordMessageEventSensor {
    public static Flux<Message> registerCommand(GatewayDiscordClient client, Command command){
        return new Builder()
                .getMessageFromClient(client)
                .filterCommand(command)
                .createMessage(command.getDisplayMessage())
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

        public Builder filterCommand(Command command){
            messageFlux = messageFlux
                    .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                    .filter(message -> message.getContent().startsWith(command.getStartCommand()));
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

        public Builder executeCommand(Command command, DiscordChatBot chatBot){
            messageFlux = messageFlux.map(message -> {
                ChatRequest request = ChatRequest.builder()
                                                .messenger("DISCORD")
                                                .command(command)
                                                .content(ChatBotUtils.parseCommand(message.getContent(), command))
                                                .build();
                DefaultChatResult chatResult = (DefaultChatResult) chatBot.execute(request);
                message.getChannel().flatMap(channel -> channel.createMessage(chatResult.getMessage()))
                        .subscribe();
                return message;
            });
            return this;
        }
    }
}
