package ru.merann.bopopov.autoshowroom.server.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.repository.ClientRepository;
import ru.merann.bopopov.autoshowroom.server.service.ConnectionService;

@Component
public class ConnectionServiceImpl implements ConnectionService {

    private static final Logger logger = LogManager.getLogger(ConnectionServiceImpl.class);

    private ClientRepository clientRepository;

    public ConnectionServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Long getClient(String username) {
        logger.log(Level.INFO, String.format("Client with username %s try to connect", username));
        Long id = clientRepository.findByName(username);
        logger.log(Level.TRACE, String.format("User id found: %s", id));
        return id;
    }
}
