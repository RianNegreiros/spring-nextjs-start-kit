package br.com.riannegreiros.backend.util;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiResponse<T>(
        LocalDateTime timestamp,
        boolean success,
        String message,
        T data,
        Map<String, String> errors) {
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(LocalDateTime.now(), true, message, data, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return success(data, "Success");
    }

    public static <T> ApiResponse<T> error(String message, Map<String, String> errors) {
        return new ApiResponse<>(LocalDateTime.now(), false, message, null, errors);
    }

    public static <T> ApiResponse<T> error(String message) {
        return error(message, null);
    }
}
