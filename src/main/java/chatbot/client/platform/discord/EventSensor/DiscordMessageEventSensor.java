package chatbot.client.platform.discord.EventSensor;

import chatbot.client.core.command.Command;
import chatbot.client.core.chat.Chat;
import chatbot.client.core.chat.ChatDto;
import chatbot.client.platform.discord.DiscordChatBot;
import chatbot.client.utils.ChatBotUtils;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ConcurrentModel;
import reactor.core.publisher.Flux;

import static chatbot.client.utils.ApiUtils.ApiResult;
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
                        .content(ChatBotUtils.parseCommand(message.getContent(), command))
                        .build();

                ChatDto dto = ChatDto.builder()
                        .chat(chat)
                        .model(new ConcurrentModel())
                        .command(command)
                        .build();

                ApiResult<ChatDto> result = chatBot.execute(dto);
                log.info("Command 실행 결과 : " + result.getResponse().getCommand() + " -> " + result.getResponse().getChat().getContent());
                message.getChannel().flatMap(channel -> channel.createMessage(result.getResponse().getChat().getContent()))
                        .subscribe();
                return message;
            });
            return this;
        }
    }
}
