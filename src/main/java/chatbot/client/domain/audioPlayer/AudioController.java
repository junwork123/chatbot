package chatbot.client.domain.audioPlayer;

import chatbot.client.core.ChatBotController;
import chatbot.client.core.Responsible;
import chatbot.client.core.message.MessageDto;
import chatbot.client.core.message.MessageTemplate;
import chatbot.client.utils.ApiUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import static chatbot.client.utils.ApiUtils.success;


@RequiredArgsConstructor
@ChatBotController
public class AudioController implements Responsible {
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
