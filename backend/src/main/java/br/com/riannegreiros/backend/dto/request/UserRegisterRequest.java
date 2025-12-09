package br.com.riannegreiros.backend.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record UserRegisterRequest(@NotEmpty(message = "Email is required") String email,
        @NotEmpty(message = "Name is required") String name,
        @NotEmpty(message = "Password is required") String password) {
}
