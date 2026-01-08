package com.medical.praticienservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {

        /*
         * CSRF protection is intentionally disabled.
         *
         * This microservice is a stateless REST API that does not use
         * HTTP sessions or browser-based authentication mechanisms.
         * All interactions are performed via REST calls (JSON),
         * therefore CSRF protection is not required and disabling it
         * does not introduce a security risk in this context.
         */
        http.csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/praticiens/**").permitAll()
                        .anyRequest().permitAll()
                )

                // Allow H2 console to be displayed in a frame (development only)
                .headers(headers ->
                        headers.frameOptions(frame -> frame.disable())
                )

                // Disable unused authentication mechanisms (stateless API)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
