package chatbot.client.global.error.exception;

import chatbot.client.global.error.model.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;
    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
