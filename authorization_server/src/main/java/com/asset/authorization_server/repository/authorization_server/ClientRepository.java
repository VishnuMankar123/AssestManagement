package com.asset.authorization_server.repository.authorization_server;


import com.asset.authorization_server.entity.authorization_server.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query(value = """
            SELECT client
            FROM Client client
            WHERE client.clientId = :clientId
            """)
    Optional<Client> findByClientId(@Param(value = "clientId") String clientId);
}
