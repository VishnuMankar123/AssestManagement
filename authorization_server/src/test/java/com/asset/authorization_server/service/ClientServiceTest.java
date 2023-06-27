package com.asset.authorization_server.service;

import com.asset.authorization_server.entity.authorization_server.AuthenticationMethod;
import com.asset.authorization_server.entity.authorization_server.Client;
import com.asset.authorization_server.entity.authorization_server.ClientTokenSettings;
import com.asset.authorization_server.entity.authorization_server.GrantType;
import com.asset.authorization_server.entity.authorization_server.RedirectURI;
import com.asset.authorization_server.entity.authorization_server.Scope;
import com.asset.authorization_server.exception.ResourceNotFoundException;
import com.asset.authorization_server.mapper.client.ClientToRegisteredClient;
import com.asset.authorization_server.mapper.client.RegisteredClientToClient;
import com.asset.authorization_server.repository.authorization_server.ClientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static com.asset.authorization_server.utils.RandomValueGenerator.generateAlphaNumericString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientToRegisteredClient clientToRegisteredClient;

    @Mock
    private RegisteredClientToClient registeredClientToClient;

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder(12);

    private static Client CLIENT;
    private static RegisteredClient REGISTERED_CLIENT;

    @BeforeAll
    static void initializeAllTestData() {
        List<String> listOfAuthenticationMethods = List.of("client_secret_basic");
        List<String> listOfGrantTypes = List.of("authorization_code");
        List<String> listOfRedirectURIs = List.of("https://example.com/auth");
        List<String> listOfScopes = List.of("openid");

        String clientSecret = generateAlphaNumericString(12);
        String clientName = generateAlphaNumericString(12);
        String clientId = generateAlphaNumericString(13);
        String tokenFormat = "self-contained";
        long timeToLiveInHours = 1L;

        CLIENT = Client.builder()
                .id(1)
                .clientId(clientId)
                .clientName(clientName)
                .clientSecret(ENCODER.encode(clientSecret))
                .authenticationMethods(listOfAuthenticationMethods.stream()
                        .map(method -> AuthenticationMethod.builder().methodName(method).build())
                        .toList())
                .grantTypes(listOfGrantTypes.stream()
                        .map(grantType -> GrantType.builder().grantTypeName(grantType).build())
                        .toList())
                .redirectURIs(listOfRedirectURIs.stream()
                        .map(redirectURI -> RedirectURI.builder().url(redirectURI).build())
                        .toList())
                .scopes(listOfScopes.stream()
                        .map(scope -> Scope.builder().scopeName(scope).build())
                        .toList())
                .clientTokenSettings(ClientTokenSettings.builder()
                        .tokenFormat(tokenFormat)
                        .timeToLiveInHours(timeToLiveInHours)
                        .build())
                .build();

        REGISTERED_CLIENT = RegisteredClient
                .withId(Integer.toString(1))
                .clientId(clientId)
                .clientName(clientName)
                .clientSecret(ENCODER.encode(clientSecret))
                .clientAuthenticationMethods(methods -> methods.addAll(listOfAuthenticationMethods.stream()
                        .map(ClientAuthenticationMethod::new)
                        .toList()))
                .authorizationGrantTypes(grantTypes -> grantTypes.addAll(listOfGrantTypes.stream()
                        .map(AuthorizationGrantType::new)
                        .toList()))
                .redirectUris(uris -> uris.addAll(listOfRedirectURIs))
                .scopes(scopes -> scopes.addAll(listOfScopes))
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(new OAuth2TokenFormat(tokenFormat))
                        .accessTokenTimeToLive(Duration.ofHours(timeToLiveInHours))
                        .build())
                .build();
    }

    @DisplayName(value = """
            Unit Test for testing the service for saving a client.
            The testing data is already initialized in the initializeAllTestData method.
            """)
    @Test
    void testSaveClient() {
        // Preconditions: defining the expected behavior of the method calls inside the method to be tested.
        given(this.clientRepository.save(any())).willReturn(CLIENT);
        given(this.registeredClientToClient.apply(REGISTERED_CLIENT)).willReturn(CLIENT);

        // Execution: calling the function to be tested.
        this.clientService.save(REGISTERED_CLIENT);

        // Verification: verify the number of method calls to the save method.
        verify(this.clientRepository, times(1)).save(CLIENT);
        verifyNoMoreInteractions(this.clientRepository);
    }

    @DisplayName(value = """
            When the correct ID is given,
            The correct registered client should be returned.
            """)
    @Test
    void givenCorrectIdFetchClientByIdWorks() {
        // Preconditions
        given(this.clientRepository.findById(1)).willReturn(Optional.of(CLIENT));
        given(this.clientToRegisteredClient.apply(CLIENT)).willReturn(REGISTERED_CLIENT);

        // Execution
        RegisteredClient returnedRegisteredClient = this.clientService.findById(Integer.toString(CLIENT.getId()));

        //Verification
        verify(this.clientRepository, times(1)).findById(1);
        assertThat(returnedRegisteredClient).isEqualTo(REGISTERED_CLIENT);
        verifyNoMoreInteractions(this.clientRepository);
    }

    @DisplayName(value = """
            When the incorrect ID is given,
            a ResourceNotFoundException will be thrown.
            """)
    @Test
    void givenIncorrectIdFetchClientByIdFails() {
        // Preconditions
        given(this.clientRepository.findById(any())).willReturn(Optional.empty());

        // Execution + Verification
        String randomString = "879";
        assertThrows(ResourceNotFoundException.class, () -> this.clientService.findById(randomString));
        verify(this.clientRepository, times(1)).findById(any());
        verifyNoMoreInteractions(this.clientRepository);
    }

    @DisplayName(value = """
            When the correct client ID is given,
            the correct registered client should be returned.
            """)
    @Test
    void givenCorrectClientIdFetchClientByClientIdWorks() {
        // Preconditions
        given(this.clientRepository.findByClientId(CLIENT.getClientId())).willReturn(Optional.of(CLIENT));
        given(this.clientToRegisteredClient.apply(CLIENT)).willReturn(REGISTERED_CLIENT);

        // Execution
        RegisteredClient returnedRegisteredClient = this.clientService.findByClientId(CLIENT.getClientId());

        // Verification
        verify(this.clientRepository, times(1)).findByClientId(CLIENT.getClientId());
        assertThat(returnedRegisteredClient).isEqualTo(REGISTERED_CLIENT);
        verifyNoMoreInteractions(this.clientRepository);
    }

    @DisplayName(value = """
            When an incorrect client ID is given,
            a ResourceNotFoundException will be thrown.
            """)
    @Test
    void givenIncorrectClientIdFetClientByClientIdFails() {
        // Preconditions
        given(this.clientRepository.findByClientId(any())).willReturn(Optional.empty());

        // Execution + Verification
        String randomString = generateAlphaNumericString(11);
        assertThrows(ResourceNotFoundException.class, () -> this.clientService.findByClientId(randomString));
        verify(this.clientRepository, times(1)).findByClientId(any());
        verifyNoMoreInteractions(this.clientRepository);
    }
}
