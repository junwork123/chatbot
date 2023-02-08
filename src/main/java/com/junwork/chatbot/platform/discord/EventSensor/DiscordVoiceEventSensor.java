package com.junwork.chatbot.platform.discord.EventSensor;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.VoiceChannel;
import discord4j.core.spec.VoiceChannelJoinSpec;
import discord4j.voice.AudioProvider;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class DiscordVoiceEventSensor {
    private DiscordVoiceEventSensor(){}

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

        public Builder joinVoiceChannel(Flux<Message> messageFlux, AudioProvider provider){
            setVoiceState(messageFlux);
            voiceChannelFlux.map(channel -> {
                channel.join(VoiceChannelJoinSpec.builder()
                                                .provider(provider)
                                                .build()).subscribe();
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
