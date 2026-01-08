package com.medical.rendezvousservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {

        /*
         * CSRF protection is intentionally disabled.
         *
         * This microservice exposes a stateless REST API and is not accessed
         * directly from a browser. It is used internally within a secured
         * microservices architecture (API Gateway + service-to-service calls).
         *
         * Since no HTTP session or cookie-based authentication is used,
         * CSRF protection is not required and can be safely disabled.
         */
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
