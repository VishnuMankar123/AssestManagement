package com.asset.resource_server.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomJwtAuthenticationTokenConverter implements Converter<Jwt, JwtAuthenticationToken> {
    @Override
    public JwtAuthenticationToken convert(Jwt token) {
        List<String> authorities = token.getClaimAsStringList("authorities");
        return new JwtAuthenticationToken(token, authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .toList());
    }
}
