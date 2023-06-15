package com.asset.authorization_server.wrapper;

import com.asset.authorization_server.entity.credentials.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class AuthorityWrapper implements GrantedAuthority {

    private final Authority authority;

    @Override
    public String getAuthority() {
        return this.authority.getRoleName();
    }
}
