package com.asset.authorization_server.service;

import com.asset.authorization_server.entity.credentials.Authority;
import com.asset.authorization_server.entity.credentials.User;
import com.asset.authorization_server.repository.credentials.UserRepository;
import com.asset.authorization_server.wrapper.UserWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder(12);

    private static User USER;
    private static UserWrapper USER_WRAPPER;

    @BeforeAll
    static void initialization() {
        USER = User.builder()
                .username(generateAlphaNumericString(12))
                .password(ENCODER.encode(generateAlphaNumericString(12)))
                .authorities(List.of(Authority.builder()
                        .roleName("test_authority")
                        .build()))
                .build();

        USER_WRAPPER = new UserWrapper(USER);
    }

    @DisplayName(value = """
            When the correct username is given,
            The correct user should be fetched.
            """)
    @Test
    void givenCorrectUsernameFindUserByUsernameWorks() {
        // Precondition
        given(this.userRepository.findByUsername(USER.getUsername())).willReturn(Optional.of(USER));

        // Execution
        UserDetails returnedUser = this.userService.loadUserByUsername(USER.getUsername());

        // Verification
        verify(this.userRepository, times(1)).findByUsername(USER.getUsername());
        assertThat(returnedUser).isInstanceOf(UserWrapper.class);
        assertThat((UserWrapper) returnedUser).usingRecursiveComparison().isEqualTo(USER_WRAPPER);
        verifyNoMoreInteractions(this.userRepository);
    }

    @DisplayName(value = """
            When an incorrect username is given,
            a UsernameNotFoundException is thrown.
            """)
    @Test
    void givenIncorrectUsernameFindUserByUsernameFails() {
        // Precondition
        given(this.userRepository.findByUsername(any())).willReturn(Optional.empty());

        // Execution + Verification
        String randomUsername = generateAlphaNumericString(15);
        assertThrows(UsernameNotFoundException.class, () -> this.userService.loadUserByUsername(randomUsername));
        verify(this.userRepository, times(1)).findByUsername(any());
        verifyNoMoreInteractions(this.userRepository);
    }
}
