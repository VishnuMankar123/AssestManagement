package com.asset.authorization_server.service;

import com.asset.authorization_server.repository.credentials.UserRepository;
import com.asset.authorization_server.wrapper.UserWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .map(UserWrapper::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }
}
