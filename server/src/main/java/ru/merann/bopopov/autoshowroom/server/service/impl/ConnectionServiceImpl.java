package ru.merann.bopopov.autoshowroom.server.service.impl;

import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.repository.ClientRepository;
import ru.merann.bopopov.autoshowroom.server.service.ConnectionService;

@Component
public class ConnectionServiceImpl implements ConnectionService {

    private ClientRepository clientRepository;

    public ConnectionServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Long getClient(String username) {
        return clientRepository.findByName(username);
    }
}
