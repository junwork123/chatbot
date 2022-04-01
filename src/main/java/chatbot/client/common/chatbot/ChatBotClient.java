package chatbot.client.common.chatbot;

import chatbot.client.common.command.Command;
import chatbot.client.common.command.CommandBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Getter
public class ChatBotClient {
    private final ChatBot chatBot;

    private ChatBotClient(Builder builder) {
        List<Command> commands = builder.commandFactory.getCommands();
        ChatBot createdChatBot = builder.chatBotFactory.CreateChatBot(commands);
        this.chatBot = createdChatBot;
    }
    public static class Builder{
        private final ChatBotFactory chatBotFactory;
        private final CommandBuilder commandFactory;
        public Builder(ChatBotFactory chatBotFactory, CommandBuilder commandFactory) {
            this.chatBotFactory = chatBotFactory;
            this.commandFactory = commandFactory;
        }

        public ChatBotClient build(){
            return new ChatBotClient(this);
        }
    }
}
