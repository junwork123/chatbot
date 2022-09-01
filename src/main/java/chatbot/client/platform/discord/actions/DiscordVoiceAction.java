package chatbot.client.platform.discord.actions;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.VoiceChannel;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class DiscordVoiceAction {

    private DiscordVoiceAction(){}
    public static class Builder{
        Flux<VoiceChannel> voiceChannelFlux;
        public Builder(){}
        public Flux<VoiceChannel> Build(){
            return voiceChannelFlux;
        }
        public Builder setVoiceState(Flux<Message> messageFlux){
            voiceChannelFlux = messageFlux.flatMap(message -> message.getAuthorAsMember())
                                    .flatMap(member -> member.getVoiceState())
                                    .flatMap(voiceState -> voiceState.getChannel());
            return this;
        }

        public Builder joinVoiceChannel(Flux<Message> messageFlux){
            setVoiceState(messageFlux);
            voiceChannelFlux.map(channel -> {
                channel.join().subscribe();
                return channel;
            }).subscribe();
            return this;
        }
        public Builder leaveVoiceChannel(Flux<Message> messageFlux){
            setVoiceState(messageFlux);
            voiceChannelFlux.map(channel -> {
                channel.getVoiceConnection()
                        .map(connection -> connection.disconnect().subscribe())
                        .subscribe();
                return channel;
            }).subscribe();
            return this;
        }
    }
}
