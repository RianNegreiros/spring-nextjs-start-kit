package br.com.riannegreiros.backend.users.dto.response;

public record UserResponse(
        Long userId,
        String email,
        String name) {
}
