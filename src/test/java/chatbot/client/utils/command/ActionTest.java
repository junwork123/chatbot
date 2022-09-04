package chatbot.client.utils.command;

import chatbot.client.core.action.Action;
import chatbot.client.core.action.ActionBuilder;
import chatbot.client.domain.lostArkAuction.LostArkAuctionController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static chatbot.client.utils.ConstUtils.JSON_PATH;

class ActionTest {
    ActionBuilder actionBuilder;
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
        actionBuilder = new ActionBuilder.Builder().addCommand("입찰", new LostArkAuctionController()).build();

        //when
        List<Action> actions = actionBuilder.getActions();

        //then
        Assertions.assertThat(actions.size()).isEqualTo(1);
        Assertions.assertThat(actions.get(0).command.getStartCommand()).isEqualTo("!입찰");
    }
}