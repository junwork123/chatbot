package chatbot.client.platform.discord.EventSensor;

import chatbot.client.core.command.Command;
import chatbot.client.core.request.ChatRequest;
import chatbot.client.core.request.MessageDto;
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
import static chatbot.client.utils.ApiUtils.*;
@Slf4j
@Getter
@RequiredArgsConstructor
public class DiscordMessageEventSensor {
    public static Flux<Message> registerCommand(DiscordChatBot chatBot, Command command){
        return new Builder()
                .getMessageFromClient(chatBot.getClient())
                .filterCommand(command)
                .createMessage(command.getDisplayMessage())
                .executeCommand(chatBot, command)
                .Build();
    }

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

        public Builder executeCommand(DiscordChatBot chatBot, Command command){
            messageFlux = messageFlux.map(message -> {
                MessageDto dto = MessageDto.builder()
                                            .messenger("DISCORD")
                                            .command(command)
                                            .content(ChatBotUtils.parseCommand(message.getContent(), command))
                                            .build();
                ApiResult<MessageDto> result = chatBot.execute(dto);
                message.getChannel().flatMap(channel -> channel.createMessage(result.getResponse().getContent()))
                        .subscribe();
                return message;
            });
            return this;
        }
    }
}
