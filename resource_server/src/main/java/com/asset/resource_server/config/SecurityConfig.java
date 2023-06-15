package com.asset.resource_server.config;

import com.asset.resource_server.converter.CustomJwtAuthenticationTokenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomJwtAuthenticationTokenConverter customJwtAuthenticationTokenConverter;

    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        // Authentication method.
        http.httpBasic(Customizer.withDefaults());

        // Resource sever configuration
        http.oauth2ResourceServer(
                resourceServer -> resourceServer.jwt(
                        jwtConfigurer -> jwtConfigurer.jwkSetUri("http://localhost:8080/oauth2/jwks")
                                .jwtAuthenticationConverter(customJwtAuthenticationTokenConverter)
                )
        );

        // Authorization configuration
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        return http.build();
    }
}
