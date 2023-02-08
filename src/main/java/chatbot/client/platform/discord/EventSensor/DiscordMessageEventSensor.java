package chatbot.client.platform.discord.EventSensor;

import chatbot.client.global.common.ApiResult.ApiEntity;
import chatbot.client.global.core.model.Chat;
import chatbot.client.global.core.model.ChatDto;
import chatbot.client.global.core.command.Command;
import chatbot.client.global.util.ChatUtils;
import chatbot.client.platform.discord.DiscordChatBot;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ConcurrentModel;
import reactor.core.publisher.Flux;

@Slf4j
@Getter
@RequiredArgsConstructor
public class DiscordMessageEventSensor {
    public static Flux<Message> registerCommand(DiscordChatBot chatBot, Command command){
        return new Builder()
                .getMessageFromClient(chatBot.getClient())
                .filterCommand(command)
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
                Chat chat = Chat.builder()
                        .messenger("DISCORD")
                        .content(ChatUtils.parseCommand(message.getContent(), command))
                        .build();

                ChatDto dto = ChatDto.builder()
                        .chat(chat)
                        .model(new ConcurrentModel())
                        .command(command)
                        .build();

                ApiEntity<?> result = chatBot.execute(dto);
                if (result.errorResponse() != null) {
                    log.error("Command 실행 결과 : " + result.errorResponse().getMessage());
                }
                ChatDto response = (ChatDto) result.response();
                log.info("Command 실행 결과 : " + command.getStartCommand() + " -> " + response.chat().getContent());

                return message;
            });
            return this;
        }
    }
}
