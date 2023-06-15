package com.asset.authorization_server.mapper.authentication_method;

import com.asset.authorization_server.entity.authorization_server.AuthenticationMethod;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClientAuthenticationMethodToAuthenticationMethod
        implements Function<ClientAuthenticationMethod, AuthenticationMethod> {
    @Override
    public AuthenticationMethod apply(ClientAuthenticationMethod clientAuthenticationMethod) {
        return AuthenticationMethod.builder()
                .methodName(clientAuthenticationMethod.getValue())
                .build();
    }
}
