package chatbot.client.controller;

import chatbot.client.common.chatbot.ChatBot;
import chatbot.client.common.chatbot.ChatBotFactory;
import chatbot.client.common.command.Command;
import chatbot.client.common.command.CommandBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequestMapping("/")
public class ChatBotController {
    private final ChatBot chatBot;

    public ChatBotController(ChatBotFactory chatBotFactory, CommandBuilder commandFactory) {
        List<Command> commands = commandFactory.getCommands();
        ChatBot createdChatBot = chatBotFactory.CreateChatBot(commands);
        this.chatBot = createdChatBot;
    }

    private ChatBotController(Builder builder) {
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

        public ChatBotController build(){
            return new ChatBotController(this);
        }
    }

//    @GetMapping
//    public String hello() {
//        return "index";
//    }

}
