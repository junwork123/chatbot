package chatbot.client.common.command;

import chatbot.client.common.chatbot.ChatBot;
import chatbot.client.service.ChatBotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static chatbot.client.common.Const.JSON_PATH;
import static chatbot.client.common.Const.JSON_PATTERN;

@Slf4j
@Getter
public class CommandBuilder {
    private final List<Command> commands;
    private CommandBuilder(Builder builder){
        this.commands = builder.commands;
    }

    public static class Builder{
        private final List<Command> commands;
        private final String path;
        private final String pattern;

        public Builder() {
            commands = new ArrayList<>();
            path = JSON_PATH;
            pattern = JSON_PATTERN;
        }
        public CommandBuilder build(){
            return new CommandBuilder(this);
        }

        public Builder addCommand(String jsonFileName, ChatBotService service) {
            String jsonString = readJsonFile(jsonFileName);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                CommandVO commandVO = objectMapper.readValue(jsonString, CommandVO.class);
                commands.add(new Command(service, commandVO));
                log.info("{} : {} {}", this.getClass(), commandVO.startCommand, commandVO.description);

            }catch (IOException e){
                e.toString();
            }
            return this;
        }

        public String readJsonFile(String jsonFileName){
            String commandFilePath = new StringBuilder(path)
                    .append(jsonFileName)
                    .append(pattern)
                    .toString();
            String result = "";
            log.info("commandFilePath = {}", commandFilePath);
            try {
                result = Files.readString(Paths.get(commandFilePath));
            }catch (IOException e){
                e.toString();
            }

            return result;
        }
    }
}
