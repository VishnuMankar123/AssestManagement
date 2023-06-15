package com.asset.authorization_server.mapper.scope;

import com.asset.authorization_server.entity.authorization_server.Scope;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StringToScope implements Function<String, Scope> {
    @Override
    public Scope apply(String scopeString) {
        return Scope.builder()
                .scopeName(scopeString)
                .build();
    }
}
