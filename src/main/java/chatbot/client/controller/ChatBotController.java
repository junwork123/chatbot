package chatbot.client.controller;

import chatbot.client.common.chatbot.ChatBot;
import chatbot.client.common.chatbot.ChatBotFactory;
import chatbot.client.common.command.Command;
import chatbot.client.common.command.CommandBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ChatBotController {
    private final ChatBot chatBot;

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

//    @GetMapping("/입찰/{price}")
//    public String hello(@RequestParam int price){
//        //71%(4인)/83%(8인)/89%(16인)

}
