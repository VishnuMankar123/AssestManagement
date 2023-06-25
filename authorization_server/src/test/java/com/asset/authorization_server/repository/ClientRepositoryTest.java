package com.asset.authorization_server.repository;

import com.asset.authorization_server.entity.authorization_server.Client;
import com.asset.authorization_server.repository.authorization_server.ClientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.asset.authorization_server.utils.RandomValueGenerator.generateAlphaNumericString;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    private static final String CLIENT_ID = "TEST-CLIENT";

    @DisplayName(value = """
            When correct client ID is passed,
            the client should be retrieved
            """)
    @Test
    void givenCorrectClientIdFetchClientByClientIdWorks() {
        Optional<Client> retrievedClient = this.clientRepository.findByClientId(CLIENT_ID);
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
}
