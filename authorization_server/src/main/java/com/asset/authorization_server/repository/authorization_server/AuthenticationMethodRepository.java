package com.asset.authorization_server.repository.authorization_server;

import com.asset.authorization_server.entity.authorization_server.AuthenticationMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationMethodRepository extends JpaRepository<AuthenticationMethod, Integer> {
}
