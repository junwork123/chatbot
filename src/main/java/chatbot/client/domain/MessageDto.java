package chatbot.client.domain;

import lombok.Getter;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

import java.time.LocalDateTime;

@Getter @Setter
public class MessageDto {
    private String user;
    private String message;
    private final LocalDateTime createAt;

    public MessageDto(MessageTemplate src) {
        this.message = src.getMessage();
        this.createAt = LocalDateTime.now();
    }
}
