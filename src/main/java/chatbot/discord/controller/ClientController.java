package chatbot.discord.controller;

import chatbot.discord.common.command.Command;
import discord4j.common.JacksonResources;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static chatbot.discord.common.Const.DISCORD_TOKEN_ID;

@Slf4j
@Controller
public class ClientController {
    private final RestClient restClient;
    private final GatewayDiscordClient discordClient;

    public ClientController() {
        this.discordClient = DiscordClientBuilder.create(System.getenv(DISCORD_TOKEN_ID)).build()
                .gateway()
                .setInitialPresence(ignore -> ClientPresence.online(ClientActivity.listening("to /commands")))
                .login()
                .block();
        this.restClient = discordClient.getRestClient();
    }

    @PostConstruct
    public void commandInit() throws IOException {
        //Create an ObjectMapper that supported Discord4J classes
        final JacksonResources d4jMapper = JacksonResources.create();

        // Convenience variables for the sake of easier to read code below.
        PathMatchingResourcePatternResolver matcher = new PathMatchingResourcePatternResolver();
        final ApplicationService applicationService = this.restClient.getApplicationService();
        final long applicationId = this.restClient.getApplicationId().block();

        //Get our commands json from resources as command data
        List<ApplicationCommandRequest> commands = new ArrayList<>();
        for (Resource resource : matcher.getResources("commands/*.json")) {
            ApplicationCommandRequest request = d4jMapper.getObjectMapper()
                    .readValue(resource.getInputStream(), ApplicationCommandRequest.class);

            commands.add(request);
        }

        /* Bulk overwrite commands. This is now idempotent, so it is safe to use this even when only 1 command
        is changed/added/removed
        */

        applicationService.bulkOverwriteGlobalApplicationCommand(applicationId, commands)
                .doOnNext(ignore -> log.debug("Successfully registered Global Commands"))
                .doOnError(e -> log.error("Failed to register global commands", e))
                .subscribe();
    }

//    public void ReadyInit(){
//        // 로아봇 상태에 따른 이벤트 종류
//        // Connect, Disconnect, Ready, Reconnect, ReconnectFail, Resume, SessionInvaildated
//        discordClient.getEventDispatcher().on(ReadyEvent.class)
//                .subscribe(event -> {
//                    User self = event.getSelf();
//                    log.info(String.format("logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
//                });
//        log.info("{} : {}", this.getClass(), "Ready 이벤트 수신자 설정됨");
//    }
//
//    @PreDestroy
//    public void destory(){
//        discordClient.onDisconnect().block();
//    }
}
