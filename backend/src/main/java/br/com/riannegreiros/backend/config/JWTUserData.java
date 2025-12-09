package br.com.riannegreiros.backend.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email) {
}
