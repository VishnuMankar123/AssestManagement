package com.asset.authorization_server.repository;

import com.asset.authorization_server.entity.credentials.Authority;
import com.asset.authorization_server.entity.credentials.User;
import com.asset.authorization_server.repository.credentials.AuthorityRepository;
import com.asset.authorization_server.repository.credentials.UserRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.asset.authorization_server.utils.RandomValueGenerator.generateAlphaNumericString;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Value(value = "${bcrypt.encoder.strength}")
    private int encoderStrength;

    private final String username = generateAlphaNumericString(15);

    @BeforeEach
    void setup() {
        PasswordEncoder encoder = new BCryptPasswordEncoder(encoderStrength);
        User user = User.builder()
                .username(this.username)
                .password(encoder.encode(generateAlphaNumericString(15)))
                .authorities(getAuthorities())
                .build();

        this.userRepository.save(user);
    }

    @DisplayName(value = """
            When the correct username is given,
            User should be fetched successfully.
            """)
    @Test
    void givenCorrectUsernameFindByUsernameWorks() {
        Optional<User> fetchedUser = this.userRepository.findByUsername(this.username);
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

    @AfterEach
    void destruction() {
        this.userRepository.deleteAll();
        this.authorityRepository.deleteAll();
    }

    private List<Authority> getAuthorities() {
        String randomRole = generateAlphaNumericString(5);
        Authority authority = Authority.builder().roleName(randomRole).build();
        return List.of(this.authorityRepository.save(authority));
    }
}
