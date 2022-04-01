package chatbot.client.common.chatbot;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Getter
public class DiscordChatBot implements ChatBot {
    private final GatewayDiscordClient client;
    private String requestPath = "/";
    private String response = "ㅋㅋㅋㅋ";

    public DiscordChatBot(GatewayDiscordClient client) {
        this.client = client;
    }

    @Override
    public void request(Object message) {
        Message requestMessage = (Message) message;
        Snowflake channelId = requestMessage.getChannelId();
        String content = requestMessage.getContent();

        recognize(content);
        //post(requestPath);
        publish(channelId, response);
    }
    public void recognize(String content) {
        content.replaceAll("!/`", "");
        String[] split = content.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : split) {
            builder.append(s);
            builder.append("/");
        }
        requestPath = builder.toString();
    }

    @GetMapping("/api")
    public String post(String requestPath) {
        return requestPath;
    }

    @Override
    public void response(String content) {

    }

    public void publish(Snowflake channelId, String content) {
        client.getChannelById(channelId)
                .ofType(MessageChannel.class)
                .flatMap(channel -> channel.createMessage(content))
                .subscribe();

        //Rest 방식 호출
        //gatewayDiscordClient.rest().getChannelById(Snowflake.of("ChannelId")).createMessage("p").subscribe();
    }
}


