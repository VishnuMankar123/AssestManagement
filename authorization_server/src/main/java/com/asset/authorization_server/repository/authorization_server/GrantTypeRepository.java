package com.asset.authorization_server.repository.authorization_server;

import com.asset.authorization_server.entity.authorization_server.GrantType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrantTypeRepository extends JpaRepository<GrantType, Integer> {
}
