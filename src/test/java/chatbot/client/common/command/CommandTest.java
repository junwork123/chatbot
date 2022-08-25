package chatbot.client.common.command;

import chatbot.client.command.Command;
import chatbot.client.command.CommandBuilder;
import chatbot.client.service.LostArkAuctionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static chatbot.client.common.Const.JSON_PATH;

class CommandTest {
    CommandBuilder commandBuilder;
    final String testPath = JSON_PATH + "ping.json";
    final String testContent =
            "{\n" +
            "  \"startCommand\": \"!test\",\n" +
            "  \"description\": \"test 커맨드\"\n" +
            "  \"response\": \"Hello World!\"\n" +
            "}";

    @BeforeEach
    public void createTestJsonCommand() throws IOException {
//        Files.writeString(Paths.get(testPath), testContent, StandardOpenOption.CREATE);
    }
    @AfterEach
    public void deleteTestJsonCommand() throws IOException {
//        Files.deleteIfExists(Paths.get(testPath));
    }

    @Test
    public void JSON_읽어오기() throws IOException{
        //given
        commandBuilder = new CommandBuilder.Builder().addCommand("입찰", new LostArkAuctionService()).build();

        //when
        List<Command> commands = commandBuilder.getCommands();

        //then
        Assertions.assertThat(commands.size()).isEqualTo(1);
        Assertions.assertThat(commands.get(0).vo.getStartCommand()).isEqualTo("!입찰");
    }
}