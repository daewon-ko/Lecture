package com.easybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.*;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

//        http.authorizeHttpRequests(request -> request.anyRequest().denyAll());
//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
//        return http.build();

//        http.authorizeHttpRequests(request -> request.anyRequest().denyAll());
//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
//        return http.build();


        http.authorizeHttpRequests(
                        request -> request.requestMatchers("/myAccount", "/myBalance", "/myLoan", "/myCards").authenticated()
                                .requestMatchers("/notices", "/contact").permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults());

        return http.build();


    }
}
