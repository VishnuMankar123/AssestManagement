package com.asset.authorization_server.mapper.grant_type;

import com.asset.authorization_server.entity.authorization_server.GrantType;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AuthorizationGrantTypeToGrantType implements Function<AuthorizationGrantType, GrantType> {
    @Override
    public GrantType apply(AuthorizationGrantType authorizationGrantType) {
        return GrantType.builder()
                .grantTypeName(authorizationGrantType.getValue())
                .build();
    }
}
