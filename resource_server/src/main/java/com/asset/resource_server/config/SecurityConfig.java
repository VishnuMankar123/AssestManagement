package com.asset.resource_server.config;

import com.asset.resource_server.converter.CustomJwtAuthenticationTokenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Value(value = "${resource.server.key-set-uri}")
    private String jwkSetUri;

    private final CustomJwtAuthenticationTokenConverter customJwtAuthenticationTokenConverter;

    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        // Authentication method.
        http.httpBasic(Customizer.withDefaults());

        // Resource sever configuration
        http.oauth2ResourceServer(
                resourceServer -> resourceServer.jwt(
                        jwtConfigurer -> jwtConfigurer.jwkSetUri(jwkSetUri)
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
