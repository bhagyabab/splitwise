package com.update.splitwse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disabling CSRF for now, you can remove later
            .authorizeRequests(auth -> auth
                .requestMatchers("/users/**").permitAll()  // Allow user routes without authentication
                .anyRequest().permitAll()  // Allow all other routes without authentication
            )
            .formLogin(form -> form.disable())  // Disable default form login page
            .httpBasic().disable();  // Disable basic auth completely for testing

        return http.build();
    }
}
