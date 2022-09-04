package chatbot.client.domain.audioPlayer;

import chatbot.client.core.ChatBotController;
import chatbot.client.core.Answerable;
import chatbot.client.core.message.MessageDto;
import chatbot.client.core.message.MessageTemplate;
import chatbot.client.utils.ApiUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static chatbot.client.utils.ApiUtils.success;


@RequiredArgsConstructor
@ChatBotController
public class AudioController implements Answerable {
    private final AudioServiceImpl service;
    @Override
    public ApiUtils.ApiResult<MessageDto> response(@NonNull MessageTemplate template, @NonNull String content) {
        return success(
                service.makeResponse(template, content)
                        .map(MessageDto::new)
                        .orElseThrow(() -> new RuntimeException("Message Error"))
        );
    }
}
