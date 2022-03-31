package chatbot.client.common.command;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static chatbot.client.common.Const.JSON_PATH;

class CommandFactoryTest {
    CommandFactory commandFactory;
    final String testPath = JSON_PATH + "test.json";
    final String testContent =
            "{\n" +
            "  \"name\": \"ping\",\n" +
            "  \"description\": \"Ping pong!\"\n" +
            "  \"response\": \"Hello World!\"\n" +
            "}";

    @BeforeEach
    public void createTestJsonCommand() throws IOException {
        Files.writeString(Paths.get(testPath), testContent, StandardOpenOption.CREATE);
    }
    @AfterEach
    public void deleteTestJsonCommand() throws IOException {
        Files.deleteIfExists(Paths.get(testPath));
    }

    @Test
    public void JSON_읽어오기() throws IOException{
        //given
        commandFactory = new CommandFactory.Builder().addCommand("ping").build();

        //when
        List<Command> commands = commandFactory.getCommands();

        //then
        Assertions.assertThat(commands.size()).isEqualTo(1);
    }

}