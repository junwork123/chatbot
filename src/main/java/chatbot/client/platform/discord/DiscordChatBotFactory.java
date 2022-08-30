package chatbot.client.platform.discord;

import chatbot.client.command.Command;
import chatbot.client.core.ChatBot;
import chatbot.client.core.ChatBotFactory;
import chatbot.client.platform.discord.audioProvider.LavaPlayerAudioProvider;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.*;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.Channel;
import discord4j.voice.AudioProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

/**
 * 로아봇 상태에 따른 이벤트 종류
 * ConnectEvent
 * DisconnectEvent
 * ReadyEvent
 * ReconnectEvent
 * ReconnectFailEvent
 * ResumeEvent
 * SessionInvalidatedEvent
 */
@Slf4j
public class DiscordChatBotFactory implements ChatBotFactory {
    @Value("${DISCORD_TOKEN_ID}")
    private String DISCORD_TOKEN_ID;

    @Override
    public ChatBot CreateChatBot(List<Command> commands) {
        GatewayDiscordClient client = DiscordClientBuilder.create(DISCORD_TOKEN_ID).build().login().block();
        AudioProvider provider = LavaPlayerAudioProvider.createAudioProvider();

        DiscordChatBot chatBot = new DiscordChatBot(client, provider);

        onCreated(chatBot);
        registerActions(chatBot);
        registerCommands(chatBot, commands);
        onDestroy(chatBot);

        return chatBot;
    }

    private void registerActions(DiscordChatBot chatBot) {
        GatewayDiscordClient client = chatBot.getClient();

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .map(MessageCreateEvent::getMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().startsWith("!join"))
                .map(message -> {
                    message.getChannel().
                            flatMap(channel -> channel.createMessage("i joined a channel"))
                            .subscribe();
                    return message;
                })
                .flatMap(message -> message.getAuthorAsMember())
                .flatMap(member -> member.getVoiceState())
                .flatMap(VoiceState::getChannel)
                // join returns a VoiceConnection which would be required if we were
                // adding disconnection features, but for now we are just ignoring it.
                .flatMap(channel -> channel.join())
                .subscribe();

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .map(MessageCreateEvent::getMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().startsWith("!out"))
                .flatMap(message -> {
                    message.getChannel().
                            flatMap(channel -> channel.createMessage("i'm out"))
                            .subscribe();
                    return message.getAuthorAsMember();
                })
                .flatMap(member -> member.getVoiceState())
                .flatMap(VoiceState::getChannel)
                .flatMap(voiceChannel -> voiceChannel.getVoiceConnection())
                .flatMap(connection -> connection.disconnect())
                .subscribe();
    }

    private void registerCommands(DiscordChatBot chatBot, List<Command> commands){
        GatewayDiscordClient client = chatBot.getClient();
        for (Command command : commands) {
            client.getEventDispatcher().on(MessageCreateEvent.class)
                    .map(MessageCreateEvent::getMessage)
                    .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                    .filter(message -> message.getContent().startsWith(command.vo.getStartCommand()))
                    .map(message -> {
                        String response = command.execute(message.getContent());
                        message.getChannel().flatMap(channel -> channel.createMessage(response))
                                .subscribe();
                        return response;
                    })
                    .subscribe();

//            메시지 보내는 방법(지우지 말 것)
//            client.getChannelById(channelId)
//                    .ofType(MessageChannel.class)
//                    .flatMap(channel -> channel.createMessage(message))
//                    .subscribe();
        }
    }

    private void onCreated(DiscordChatBot chatBot) {
        chatBot.getClient().getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    User self = event.getSelf();
                    log.info(String.format("logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
                });
        log.info("{} : {}", this.getClass(), "Ready 이벤트 수신자 설정됨");
    }
    private void onDestroy(DiscordChatBot chatBot) {
        chatBot.getClient().onDisconnect().block();
    }
}
