package chatbot.client.core.request;

import chatbot.client.core.command.Command;
import chatbot.client.core.result.ChatResult;
import chatbot.client.utils.MapperUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class MessageDto {
    private String messenger;
    private String messageNo;
    private String user;
    private String room;
    private String thread;
    private String content;
    private Command command;

    public MessageDto(){}
    @Builder
    public MessageDto(String messenger, String messageNo, String user, String room, String thread, String content, Command command) {
        this.messenger = messenger;
        this.messageNo = messageNo;
        this.user = user;
        this.room = room;
        this.thread = thread;
        this.content = content;
        this.command = command;
    }

    public static MessageDto of(ChatRequest chatRequest){
        ModelMapper mapper = MapperUtils.getMapper();
        MessageDto dto = mapper.map(chatRequest, MessageDto.class);
        return dto;
    }

    public static MessageDto of(ChatResult chatResult){
        ModelMapper mapper = MapperUtils.getMapper();
        MessageDto dto = mapper.map(chatResult, MessageDto.class);
        return dto;
    }
}
