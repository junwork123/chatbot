package chatbot.client.platform.discord;

import chatbot.client.core.event.Event;
import chatbot.client.core.event.EventSensor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class DiscordEventSensor implements EventSensor {
    private DiscordDispatcher dispatcher;

    @Override
    public List<Event> sensingEvent() {
        return null;
    }
}
