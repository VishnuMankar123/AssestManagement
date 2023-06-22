package com.asset.authorization_server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationSecurityConfig {

    @Value(value = "${bcrypt.encoder.strength}")
    private int encoderStrength;

    @Bean
    public SecurityFilterChain applicationFilterChain(HttpSecurity http) throws Exception {
        // Authentication method
        http.formLogin(Customizer.withDefaults());

        //Authorization
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(encoderStrength);
    }
}
