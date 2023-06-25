package com.asset.authorization_server.repository;

import com.asset.authorization_server.entity.credentials.User;
import com.asset.authorization_server.repository.credentials.UserRepository;
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
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private static final String USERNAME = /*generateAlphaNumericString(15)*/"TEST-USER";

    @DisplayName(value = """
            When the correct username is given,
            User should be fetched successfully.
            """)
    @Test
    void givenCorrectUsernameFindByUsernameWorks() {
        Optional<User> fetchedUser = this.userRepository.findByUsername(USERNAME);
        Assertions.assertThat(fetchedUser).isPresent();
    }

    @DisplayName(value = """
            When the incorrect username is given,
            the retrieved optional user should not be present.
            """)
    @Test
    void givenIncorrectUsernameFindByUsernameFails() {
        String incorrectUsername = generateAlphaNumericString(17);
        Optional<User> fetchedUser = this.userRepository.findByUsername(incorrectUsername);
        Assertions.assertThat(fetchedUser).isNotPresent();
    }
}
