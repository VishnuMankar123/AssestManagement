package com.asset.authorization_server.repository.authorization_server;

import com.asset.authorization_server.entity.authorization_server.RedirectURI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedirectURIRepository extends JpaRepository<RedirectURI, Integer> {
}
