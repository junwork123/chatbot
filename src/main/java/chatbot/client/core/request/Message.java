package chatbot.client.core.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Message {
    private final String messenger;
    private final String messageNo;
    private final String user;
    private final String room;
    private final String thread;
    private final String content;
}
