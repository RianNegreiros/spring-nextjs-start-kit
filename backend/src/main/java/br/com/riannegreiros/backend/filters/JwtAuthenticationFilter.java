package br.com.riannegreiros.backend.filters;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.riannegreiros.backend.config.JWTUserData;
import br.com.riannegreiros.backend.config.TokenConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final TokenConfig tokenConfig;

    public JwtAuthenticationFilter(TokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        logger.debug("JWT Filter - Processing: {} {}", request.getMethod(), request.getRequestURI());

        String authHeader = request.getHeader("Authorization");

        if (Strings.isNotEmpty(authHeader) && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer ".length());

            Optional<JWTUserData> userData = tokenConfig.validateToken(token);

            if (userData.isPresent()) {
                JWTUserData user = userData.get();
                logger.info("User authenticated: {} (ID: {})", user.email(), user.userId());

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
                        null, null);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            } else {
                logger.warn("Token validation failed");
            }
        }

        filterChain.doFilter(request, response);
    }
}
