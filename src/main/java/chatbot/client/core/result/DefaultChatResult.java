package chatbot.client.core.result;

import lombok.Getter;

@Getter
public class DefaultChatResult extends SimpleMessageChatResult {
    public static DefaultChatResult NONE = builder().build();

    private final String room;
    private final String thread;

    public DefaultChatResult(Builder builder) {
        this.message = builder.message;
        this.room = builder.room;
        this.thread = builder.thread;
    }

    @Override
    public String getRoom() {
        return room;
    }

    @Override
    public String getThread() {
        return thread;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String message;
        private String room;
        private String thread;

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public Builder room(String room){
            this.room = room;
            return this;
        }

        public Builder thread(String thread){
            this.thread = thread;
            return this;
        }

        public DefaultChatResult build() {
            if(this.message == null){
                return NONE;
            }
            return new DefaultChatResult(this);
        }
        public static Builder FAILED = new Builder()
                .message("해당 기능은 장애 상태 입니다");
        public static Builder GREETING = new Builder()
                .message("안녕하세요.\n '기능'을 참고하세요");
    }

}
