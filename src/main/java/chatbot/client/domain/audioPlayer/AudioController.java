package chatbot.client.domain.audioPlayer;

import chatbot.client.core.ChatBotController;
import chatbot.client.message.MessageDto;
import chatbot.client.message.MessageTemplate;
import chatbot.client.utils.ApiUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static chatbot.client.utils.ApiUtils.success;


@RequiredArgsConstructor
@Controller
public class AudioController implements ChatBotController {
    private final AudioService service;
    @Override
    public ApiUtils.ApiResult<MessageDto> response(@NonNull MessageTemplate template, @NonNull String content) {
        return success(
                service.makeResponse(template, content)
                        .map(MessageDto::new)
                        .orElseThrow(() -> new RuntimeException("Message Error"))
        );
    }
}
