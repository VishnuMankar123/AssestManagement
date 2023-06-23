package com.asset.authorization_server.service;

import com.asset.authorization_server.exception.ResourceNotFoundException;
import com.asset.authorization_server.mapper.client.ClientToRegisteredClient;
import com.asset.authorization_server.mapper.client.RegisteredClientToClient;
import com.asset.authorization_server.repository.authorization_server.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;
    private final RegisteredClientToClient registeredClientToClient;
    private final ClientToRegisteredClient clientToRegisteredClient;


    @Override
    public void save(RegisteredClient registeredClient) {
        this.clientRepository.save(this.registeredClientToClient.apply(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        return this.clientRepository.findById(Integer.parseInt(id))
                .map(clientToRegisteredClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return this.clientRepository.findByClientId(clientId)
                .map(clientToRegisteredClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "clientId", clientId));
    }
}
