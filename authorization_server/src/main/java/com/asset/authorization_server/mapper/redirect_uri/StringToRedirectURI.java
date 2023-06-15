package com.asset.authorization_server.mapper.redirect_uri;

import com.asset.authorization_server.entity.authorization_server.RedirectURI;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StringToRedirectURI implements Function<String, RedirectURI> {
    @Override
    public RedirectURI apply(String urlString) {
        return RedirectURI.builder()
                .url(urlString)
                .build();
    }
}
