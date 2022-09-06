package chatbot.client.core.chat;

import chatbot.client.core.command.Command;
import chatbot.client.utils.MapperUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

@Getter
@Setter
public class ChatDto {
    private Chat chat;
    private Command command;
    private Model model;

    public ChatDto(){}

    @Builder
    public ChatDto(Chat chat, Command command, Model model) {
        this.chat = chat;
        this.command = command;
        this.model = model;
    }

    public ChatRequest toRequestEntity(){
//        ModelMapper modelMapper = MapperUtils.getMapper();
//        modelMapper.typeMap(MessageDto.class, ChatRequest.class).addMappings(mapper -> {
//           mapper.map(MessageDto::getModel, ChatRequest::setModel);
//        });
        return MapperUtils.getMapper().map(this, ChatRequest.class);
    }
    public ChatResult toResultEntity(){
        return MapperUtils.getMapper().map(this, ChatResult.class);
    }

    public static ChatDto of(ChatRequest chatRequest){
        ModelMapper mapper = MapperUtils.getMapper();
        ChatDto dto = mapper.map(chatRequest, ChatDto.class);
        return dto;
    }

    public static ChatDto of(ChatResult chatResult){
        ModelMapper mapper = MapperUtils.getMapper();
        ChatDto dto = mapper.map(chatResult, ChatDto.class);
        return dto;
    }
}
