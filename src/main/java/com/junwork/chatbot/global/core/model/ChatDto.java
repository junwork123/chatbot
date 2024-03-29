package com.junwork.chatbot.global.core.model;

import com.junwork.chatbot.global.core.command.Command;
import com.junwork.chatbot.global.util.MapperUtils;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

public record ChatDto (Chat chat, Command command, Model model) {

    @Builder
    public ChatDto {
    }

    public ChatRequest toRequestEntity(){
        return MapperUtils.getMapper().map(this, ChatRequest.class);
    }
    public ChatResult toResultEntity(){
        return MapperUtils.getMapper().map(this, ChatResult.class);
    }

    public static ChatDto of(ChatRequest chatRequest){
        ModelMapper mapper = MapperUtils.getMapper();
        return mapper.map(chatRequest, ChatDto.class);
    }

    public static ChatDto of(ChatResult chatResult){
        ModelMapper mapper = MapperUtils.getMapper();
        return mapper.map(chatResult, ChatDto.class);
    }
}
