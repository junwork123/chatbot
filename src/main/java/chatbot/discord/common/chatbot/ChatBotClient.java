package chatbot.discord.common.chatbot;

import chatbot.discord.common.command.CommandFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ChatBotClient {
    private final ChatBotFactory chatBotFactory;
    private final CommandFactory commandFactory;

    private ChatBotClient(Builder builder) {
        this.chatBotFactory = builder.chatBotFactory;
        this.commandFactory = builder.commandFactory;
    }
    public static class Builder{
        private final ChatBotFactory chatBotFactory;
        private final CommandFactory commandFactory;
        public Builder(ChatBotFactory chatBotFactory, CommandFactory commandFactory) {
            this.chatBotFactory = chatBotFactory;
            this.commandFactory = commandFactory;
        }

        public ChatBotClient build(){
            return new ChatBotClient(this);
        }
    }
}
