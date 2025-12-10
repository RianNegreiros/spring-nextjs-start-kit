package br.com.riannegreiros.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.riannegreiros.backend.config.JWTUserData;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestAuthController {

    private static final Logger logger = LoggerFactory.getLogger(TestAuthController.class);

    @GetMapping("/headers")
    public ResponseEntity<Map<String, Object>> checkHeaders(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("authHeaderReceived", authHeader != null);
        response.put("authHeader", authHeader);
        response.put("message", "This endpoint shows received headers");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth-status")
    public ResponseEntity<Map<String, Object>> checkAuthStatus() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        logger.info("📍 /auth-status endpoint called");
        logger.info("🔐 Authentication object: {}", auth);
        logger.info("🔐 Is authenticated: {}", auth != null && auth.isAuthenticated());

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());

        if (auth != null) {
            response.put("isAuthenticated", auth.isAuthenticated());
            response.put("principal", auth.getPrincipal().toString());
            response.put("principalType", auth.getPrincipal().getClass().getSimpleName());
        } else {
            response.put("isAuthenticated", false);
            response.put("message", "No authentication object found");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/jwt-user")
    public ResponseEntity<Map<String, Object>> getJwtUserData() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        logger.info("📍 /jwt-user endpoint called");

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());

        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();

            if (principal instanceof JWTUserData) {
                JWTUserData userData = (JWTUserData) principal;
                response.put("authenticated", true);
                response.put("userId", userData.userId());
                response.put("email", userData.email());
            } else {
                response.put("authenticated", false);
                response.put("message", "Principal is not JWTUserData");
            }
        } else {
            response.put("authenticated", false);
            response.put("message", "No authentication found");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "JWT Authentication Service",
                "timestamp", LocalDateTime.now().toString()));
    }
}
