package chatbot.client.global.error.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private int status;
    private String code;
    private List<FieldError> errors;

    @Builder
    public ErrorResponse(String message, int status, String code, List<FieldError> errors) {
        this.message = message;
        this.status = status;
        this.code = code;
        this.errors = errors;
    }
    public static ErrorResponse of(Exception e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .code(HttpStatus.BAD_REQUEST.name())
                .build();
    }
    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .build();
    }
    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .errors(bindingResult.getFieldErrors())
                .build();
    }
}