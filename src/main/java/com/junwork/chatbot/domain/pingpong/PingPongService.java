package com.junwork.chatbot.domain.pingpong;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PingPongService{
    public String getPingpong(){
        return "pong";
    }
}

