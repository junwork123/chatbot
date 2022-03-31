package chatbot.discord.common.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static chatbot.discord.common.Const.JSON_PATH;
import static chatbot.discord.common.Const.JSON_PATTERN;

@Slf4j
@Getter
public class CommandFactory {
    private final List<Command> commands;

    private CommandFactory(Builder builder){
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
        public CommandFactory build(){
            return new CommandFactory(this);
        }

        public Builder addCommand(String jsonFileName) {
            String jsonString = readJsonFile(jsonFileName);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Command value = objectMapper.readValue(jsonString, Command.class);
                commands.add(value);
                log.info("{} : {} {}", this.getClass(), value.startCommand, value.getDescription());

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
