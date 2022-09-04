package chatbot.client.domain.lostArkAuction;

import chatbot.client.core.message.MessageTemplate;
import lombok.NonNull;

import java.util.Optional;

public interface LostArkAuctionService {
    public Optional<MessageTemplate> makeResponse(@NonNull MessageTemplate template, @NonNull String content);
}
