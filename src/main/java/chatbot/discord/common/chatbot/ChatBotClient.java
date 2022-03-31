package chatbot.discord.common.chatbot;

import chatbot.discord.common.command.Command;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
public class ChatBotClient {
    private final ChatBotFactory chatBotFactory;
    private final List<Command> commands;

    private ChatBotClient(Builder builder) {
        this.chatBotFactory = builder.chatBotFactory;
        this.commands = builder.commands;
    }
    public static class Builder{
        private final ChatBotFactory chatBotFactory;
        private final List<Command> commands = new ArrayList<>();
        public Builder(ChatBotFactory chatBotFactory) {
            this.chatBotFactory = chatBotFactory;
        }
        public Builder addCommand(Command command){
            this.commands.add(command);
            return this;
        }
        public ChatBotClient build(){
            return new ChatBotClient(this);
        }
    }
}
