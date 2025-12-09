package br.com.riannegreiros.backend.dto.response;

public record UserRegisterResponse(String token, String name, String email) {
}
