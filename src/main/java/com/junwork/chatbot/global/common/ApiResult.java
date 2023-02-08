package com.junwork.chatbot.global.common;

import com.junwork.chatbot.global.error.model.ErrorCode;
import com.junwork.chatbot.global.error.model.ErrorResponse;
import org.springframework.validation.BindingResult;

public class ApiResult {
    public static <T> ApiEntity<T> success(T response) {
        return new ApiEntity<>(true, response, null);
    }
    public static ApiEntity<?> error(ErrorResponse errorResponse) {
        return new ApiEntity<>(false, "", errorResponse);
    }

    public static ApiEntity<?> fail(BindingResult bindingResult) {
        return new ApiEntity<>(false, "", ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, bindingResult));
    }
    public record ApiEntity<T>(boolean isSuccess, T response, ErrorResponse errorResponse) {
    }
}