package com.asset.authorization_server.mapper.token_settings;

import com.asset.authorization_server.entity.authorization_server.ClientTokenSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.function.Function;

@Component
public class ClientTokenSettingsToTokenSettings implements Function<ClientTokenSettings, TokenSettings> {
    @Override
    public TokenSettings apply(ClientTokenSettings clientTokenSettings) {
        return TokenSettings.builder()
                .accessTokenFormat(new OAuth2TokenFormat(clientTokenSettings.getTokenFormat()))
                .accessTokenTimeToLive(Duration.ofHours(clientTokenSettings.getTimeToLiveInHours()))
                .build();
    }
}
