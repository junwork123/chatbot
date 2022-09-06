package chatbot.client.utils;

import org.modelmapper.ModelMapper;
public class MapperUtils {
    private static ModelMapper mapper = new ModelMapper();

    public static ModelMapper getMapper(){
        return mapper;
    }
}
