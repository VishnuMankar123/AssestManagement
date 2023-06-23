package com.asset.authorization_server.repository.credentials;

import com.asset.authorization_server.entity.credentials.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
