package com.asset.authorization_server.mapper.client;

import com.asset.authorization_server.entity.authorization_server.Client;
import com.asset.authorization_server.mapper.authentication_method.ClientAuthenticationMethodToAuthenticationMethod;
import com.asset.authorization_server.mapper.grant_type.AuthorizationGrantTypeToGrantType;
import com.asset.authorization_server.mapper.redirect_uri.StringToRedirectURI;
import com.asset.authorization_server.mapper.scope.StringToScope;
import com.asset.authorization_server.mapper.token_settings.TokenSettingsToClientTokenSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class RegisteredClientToClient implements Function<RegisteredClient, Client> {

    private final ClientAuthenticationMethodToAuthenticationMethod clientAuthMethodToAuthMethod;
    private final AuthorizationGrantTypeToGrantType authorizationGrantTypeToGrantType;
    private final StringToScope stringToScope;
    private final StringToRedirectURI stringToRedirectURI;
    private final TokenSettingsToClientTokenSettings tokenSettingsToClientTokenSettings;

    @Override
    public Client apply(RegisteredClient registeredClient) {
        return Client.builder()
                .id(Integer.parseInt(registeredClient.getId()))
                .clientName(registeredClient.getClientName())
                .clientId(registeredClient.getClientId())
                .clientSecret(registeredClient.getClientSecret())
                .authenticationMethods(registeredClient.getClientAuthenticationMethods()
                        .stream()
                        .map(clientAuthMethodToAuthMethod)
                        .toList())
                .grantTypes(registeredClient.getAuthorizationGrantTypes()
                        .stream()
                        .map(authorizationGrantTypeToGrantType)
                        .toList())
                .scopes(registeredClient.getScopes()
                        .stream()
                        .map(stringToScope)
                        .toList())
                .redirectURIs(registeredClient.getRedirectUris()
                        .stream()
                        .map(stringToRedirectURI)
                        .toList())
                .clientTokenSettings(tokenSettingsToClientTokenSettings.apply(registeredClient.getTokenSettings()))
                .build();
    }
}
