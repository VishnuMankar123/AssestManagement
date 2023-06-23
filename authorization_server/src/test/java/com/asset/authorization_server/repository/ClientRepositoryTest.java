package com.asset.authorization_server.repository;

import com.asset.authorization_server.entity.authorization_server.AuthenticationMethod;
import com.asset.authorization_server.entity.authorization_server.Client;
import com.asset.authorization_server.entity.authorization_server.ClientTokenSettings;
import com.asset.authorization_server.entity.authorization_server.GrantType;
import com.asset.authorization_server.entity.authorization_server.RedirectURI;
import com.asset.authorization_server.entity.authorization_server.Scope;
import com.asset.authorization_server.mapper.authentication_method.ClientAuthenticationMethodToAuthenticationMethod;
import com.asset.authorization_server.mapper.grant_type.AuthorizationGrantTypeToGrantType;
import com.asset.authorization_server.mapper.redirect_uri.StringToRedirectURI;
import com.asset.authorization_server.mapper.scope.StringToScope;
import com.asset.authorization_server.repository.authorization_server.AuthenticationMethodRepository;
import com.asset.authorization_server.repository.authorization_server.ClientRepository;
import com.asset.authorization_server.repository.authorization_server.ClientTokenSettingsRepository;
import com.asset.authorization_server.repository.authorization_server.GrantTypeRepository;
import com.asset.authorization_server.repository.authorization_server.RedirectURIRepository;
import com.asset.authorization_server.repository.authorization_server.ScopeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.asset.authorization_server.utils.RandomValueGenerator.generateAlphaNumericString;

@ExtendWith(SpringExtension.class)
@Import(value = {
        StringToRedirectURI.class,
        StringToScope.class,
        AuthorizationGrantTypeToGrantType.class,
        ClientAuthenticationMethodToAuthenticationMethod.class
})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AuthenticationMethodRepository authenticationMethodRepository;

    @Autowired
    private GrantTypeRepository grantTypeRepository;

    @Autowired
    private ScopeRepository scopeRepository;

    @Autowired
    private RedirectURIRepository redirectURIRepository;

    @Autowired
    private ClientTokenSettingsRepository clientTokenSettingsRepository;

    @Autowired
    private StringToRedirectURI stringToRedirectURI;

    @Autowired
    private StringToScope stringToScope;

    @Autowired
    private AuthorizationGrantTypeToGrantType authorizationGrantTypeToGrantType;

    @Autowired
    private ClientAuthenticationMethodToAuthenticationMethod clientAuthMethodToAuthMethod;

    @Value(value = "${bcrypt.encoder.strength}")
    private int encoderStrength;

    private final String password = generateAlphaNumericString(10);
    private final String clientId = generateAlphaNumericString(10);

    @BeforeEach
    public void setup() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(this.encoderStrength);

        Client singleClient = Client.builder()
                .clientId(clientId)
                .clientName(generateAlphaNumericString(10))
                .clientSecret(passwordEncoder.encode(password))
                .grantTypes(getGrantTypes())
                .authenticationMethods(getAuthenticationMethods())
                .scopes(getScopes())
                .redirectURIs(getRedirectURIs())
                .clientTokenSettings(getClientTokenSettings())
                .build();

        this.clientRepository.save(singleClient);
    }

    @DisplayName(value = """
            When correct client ID is passed,
            the client should be retrieved
            """)
    @Test
    void givenCorrectClientIdFetchClientByClientIdWorks() {
        Optional<Client> retrievedClient = this.clientRepository.findByClientId(this.clientId);
        Assertions.assertThat(retrievedClient).isPresent();
    }

    @DisplayName(value = """
            When incorrect client ID is passed,
            the retrieved optional client should be empty
            """)
    @Test
    void givenIncorrectClientIdFetClientByClientIdFails() {
        String incorrectClientId = generateAlphaNumericString(12);
        Optional<Client> retrievedClient = this.clientRepository.findByClientId(incorrectClientId);
        Assertions.assertThat(retrievedClient).isNotPresent();
    }

    @AfterEach
    void destruction() {
        this.clientRepository.deleteAll();
        this.authenticationMethodRepository.deleteAll();
        this.grantTypeRepository.deleteAll();
        this.scopeRepository.deleteAll();
        this.redirectURIRepository.deleteAll();
        this.clientTokenSettingsRepository.deleteAll();
    }

    private List<AuthenticationMethod> getAuthenticationMethods() {
        AuthenticationMethod authenticationMethod = AuthenticationMethod.builder().methodName("client_secret_basic").build();
        return List.of(this.authenticationMethodRepository.save(authenticationMethod));
    }

    private List<GrantType> getGrantTypes() {
        GrantType grantType = GrantType.builder().grantTypeName("authorization_code").build();
        return List.of(this.grantTypeRepository.save(grantType));
    }

    private List<Scope> getScopes() {
        Scope scope = Scope.builder().scopeName(OidcScopes.OPENID).build();
        return List.of(this.scopeRepository.save(scope));
    }

    private List<RedirectURI> getRedirectURIs() {
        RedirectURI redirectURI = RedirectURI.builder().url("https://example.com/auth").build();
        return List.of(this.redirectURIRepository.save(redirectURI));
    }

    private ClientTokenSettings getClientTokenSettings() {
        ClientTokenSettings clientTokenSettings = ClientTokenSettings.builder()
                .tokenFormat(OAuth2TokenFormat.SELF_CONTAINED.getValue())
                .timeToLiveInHours(1L)
                .build();

        return this.clientTokenSettingsRepository.save(clientTokenSettings);
    }
}
