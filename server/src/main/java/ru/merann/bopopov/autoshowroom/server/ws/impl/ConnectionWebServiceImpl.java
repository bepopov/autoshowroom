package ru.merann.bopopov.autoshowroom.server.ws.impl;

import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.service.ConnectionService;
import ru.merann.bopopov.autoshowroom.server.ws.ConnectionWebService;

import javax.jws.WebService;

@Component
@WebService(serviceName = "connectionService", portName = "connectionServicePort")
public class ConnectionWebServiceImpl implements ConnectionWebService {

    private ConnectionService connectionService;

    public ConnectionWebServiceImpl(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Override
    public Long connect(String username) {
        return connectionService.getClientId(username);
    }
}
