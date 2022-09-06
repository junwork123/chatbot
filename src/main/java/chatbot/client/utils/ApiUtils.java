package chatbot.client.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Method;
import java.util.Map;

public class ApiUtils {

    public static <T> ApiResult<T> success(T response, Map<Class<?>, Method> map) {
        return new ApiResult<>(true, response, map, null);
    }

    public static ApiResult<?> error(Throwable throwable, HttpStatus status) {
        return new ApiResult<>(false, null, null, new ApiError(throwable, status));
    }

    public static ApiResult<?> error(String message, HttpStatus status) {
        return new ApiResult<>(false, null, null, new ApiError(message, status));
    }

    public static class ApiError {
        private final String message;
        private final int status;

        ApiError(Throwable throwable, HttpStatus status) {
            this(throwable.getMessage(), status);
        }

        ApiError(String message, HttpStatus status) {
            this.message = message;
            this.status = status.value();
        }

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("message", message)
                    .append("status", status)
                    .toString();
        }
    }
    @Getter
    @RequiredArgsConstructor
    public static class ApiResult<T> {
        private final boolean success;
        private final T response;

        private final Map<Class<?>, Method> controllerMap;
        private final ApiError error;

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("success", success)
                    .append("response", response)
                    .append("error", error)
                    .toString();
        }
    }

}