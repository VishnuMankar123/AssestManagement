package com.asset.authorization_server.repository.authorization_server;

import com.asset.authorization_server.entity.authorization_server.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScopeRepository extends JpaRepository<Scope, Integer> {
}
