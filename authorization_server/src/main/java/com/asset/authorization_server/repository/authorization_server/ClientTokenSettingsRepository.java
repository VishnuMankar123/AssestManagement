package com.asset.authorization_server.repository.authorization_server;

import com.asset.authorization_server.entity.authorization_server.ClientTokenSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientTokenSettingsRepository extends JpaRepository<ClientTokenSettings, Integer> {
}
