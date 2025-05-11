package com.vaccine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(
                                                                "/", "/error", "/swagger-ui/**", "/v3/api-docs/**")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .oauth2Login(login -> login
                                                .defaultSuccessUrl("http://localhost:5173/dashboard", true))
                                .cors();

                return http.build();
        }
}
