package chatbot.client.global.util;

import org.modelmapper.ModelMapper;
public class MapperUtils {
    private static final ModelMapper mapper = new ModelMapper();

    public static ModelMapper getMapper(){
        return mapper;
    }
}
