package com.asset.authorization_server.mapper.client;

import com.asset.authorization_server.entity.authorization_server.Client;
import com.asset.authorization_server.entity.authorization_server.RedirectURI;
import com.asset.authorization_server.entity.authorization_server.Scope;
import com.asset.authorization_server.mapper.authentication_method.AuthenticationMethodToClientAuthenticationMethod;
import com.asset.authorization_server.mapper.grant_type.GrantTypeToAuthorizationGrantType;
import com.asset.authorization_server.mapper.token_settings.ClientTokenSettingsToTokenSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ClientToRegisteredClient implements Function<Client, RegisteredClient> {

    private final AuthenticationMethodToClientAuthenticationMethod authMethodToClientAuthMethod;
    private final GrantTypeToAuthorizationGrantType grantTypeToAuthorizationGrantType;
    private final ClientTokenSettingsToTokenSettings clientTokenSettingsToTokenSettings;

    @Override
    public RegisteredClient apply(Client client) {
        return RegisteredClient.withId(client.getId().toString())
                .clientName(client.getClientName())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientAuthenticationMethods(authenticationMethods -> authenticationMethods.addAll(client.getAuthenticationMethods()
                        .stream()
                        .map(authMethodToClientAuthMethod)
                        .toList()))
                .authorizationGrantTypes(grantTypes -> grantTypes.addAll(client.getGrantTypes()
                        .stream()
                        .map(grantTypeToAuthorizationGrantType)
                        .toList()))
                .scopes(scopeList -> scopeList.addAll(client.getScopes()
                        .stream()
                        .map(Scope::getScopeName)
                        .toList()))
                .redirectUris(uris -> uris.addAll(client.getRedirectURIs()
                        .stream()
                        .map(RedirectURI::getUrl)
                        .toList()))
                .tokenSettings(clientTokenSettingsToTokenSettings.apply(client.getClientTokenSettings()))
                .build();
    }
}
