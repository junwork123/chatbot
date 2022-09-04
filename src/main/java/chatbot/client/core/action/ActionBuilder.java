package chatbot.client.core.action;

import chatbot.client.core.Answerable;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static chatbot.client.utils.ConstUtils.JSON_PATH;
import static chatbot.client.utils.ConstUtils.JSON_PATTERN;

@Slf4j
@Getter
public class ActionBuilder {
    private final List<Action> actions;
    private ActionBuilder(Builder builder){
        this.actions = builder.actions;
    }

    public static class Builder{
        private final List<Action> actions;
        private final String path;
        private final String pattern;

        public Builder() {
            actions = new ArrayList<>();
            path = JSON_PATH;
            pattern = JSON_PATTERN;
        }
        public ActionBuilder build(){
            return new ActionBuilder(this);
        }

        public Builder addCommand(String jsonFileName, Answerable controller) {
            String jsonString = readJsonFile(jsonFileName);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Command commandVO = objectMapper.readValue(jsonString, Command.class);
                actions.add(new Action(controller, commandVO));
                log.info("커맨드 추가 : {} {}", commandVO.getStartCommand(), commandVO.getDescription());

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
