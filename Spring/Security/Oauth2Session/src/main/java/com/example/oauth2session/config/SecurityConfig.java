package com.example.oauth2session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  // 시큐리티 활성화
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf((csrf)-> csrf.disable());

        http
                .formLogin((login) -> login.disable());
        http
                .httpBasic((httpBasic) -> httpBasic.disable());

        http
                .oauth2Login(Customizer.withDefaults());

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
                        .anyRequest().authenticated());




        return http.build();

    }
}
