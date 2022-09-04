package chatbot.client.core.action;

public enum PredefinedCommand {
    JOIN("!join", "음성채팅 입장", "i joined a channel"),
    OUT("!out", "음성채팅 퇴장", "i'm out");
    private final Command command;
    PredefinedCommand(String startCommand, String description, String displayMessage) {
        this.command = new Command();
        this.command.setStartCommand(startCommand);
        this.command.setDescription(description);
        this.command.setDisplayMessage(displayMessage);
    }
    public String getStartCommand(){ return this.command.getStartCommand(); }
    public String getDescription(){ return this.command.getDescription(); }
    public String getDisplayMessage(){ return this.command.getDisplayMessage(); }
}
