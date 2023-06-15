package com.asset.authorization_server.mapper.authentication_method;

import com.asset.authorization_server.entity.authorization_server.AuthenticationMethod;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AuthenticationMethodToClientAuthenticationMethod
        implements Function<AuthenticationMethod, ClientAuthenticationMethod> {
    @Override
    public ClientAuthenticationMethod apply(AuthenticationMethod authenticationMethod) {
        return new ClientAuthenticationMethod(authenticationMethod.getMethodName());
    }
}
