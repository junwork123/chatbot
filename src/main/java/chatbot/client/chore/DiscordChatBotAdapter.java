package chatbot.client.chore;

//@Slf4j
//public class DiscordChatBotAdapter implements ChatBotAdapter<GatewayDiscordClient> {
//    @Override
//    public boolean supports(ChatBotFactory factory) throws IllegalAccessException{
//        if(factory.CreateChatBot() instanceof GatewayDiscordClient)
//            return true;
//        else
//            throw new IllegalAccessException();
//    }
//
//    @Override
//    public GatewayDiscordClient adapt(ChatBot chatBot) {
//        try {
//            supports(factory);
//        }catch (IllegalAccessException e){
//            e.toString();
//        }
//        return (GatewayDiscordClient) factory.CreateChatBot();
//    }
//
////
////    public void ReadyInit(){
////        // 로아봇 상태에 따른 이벤트 종류
////        // Connect, Disconnect, Ready, Reconnect, ReconnectFail, Resume, SessionInvaildated
////        discordClient.getEventDispatcher().on(ReadyEvent.class)
////                .subscribe(event -> {
////                    User self = event.getSelf();
////                    log.info(String.format("logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
////                });
////        log.info("{} : {}", this.getClass(), "Ready 이벤트 수신자 설정됨");
////    }
////
//
//}
