package ru.merann.bopopov.autoshowroom.soapclient.service.impl;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import ru.merann.bopopov.autoshowroom.server.ws.ConnectionWebService;
import ru.merann.bopopov.autoshowroom.soapclient.service.ConnectionService;

@Service
@ShellComponent
public class ConnectionServiceImpl implements ConnectionService {

    private boolean connected;
    private String username;
    private Long clientId;
    private ConnectionWebService connectionWebService;

    public ConnectionServiceImpl() {
        ru.merann.bopopov.autoshowroom.server.ws.impl.ConnectionService connectionService =
                new ru.merann.bopopov.autoshowroom.server.ws.impl.ConnectionService();
        this.connectionWebService = connectionService.getConnectionServicePort();
    }

    @Override
    @ShellMethod("Connect using username")
    public void connect(String username) {
        this.username = username;
        this.connected = true;
        this.clientId = connectionWebService.connect(username);
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @ShellMethod("Disconnect the server")
    public void disconnect() {
        this.connected = false;
    }

    @Override
    public Long getClientId() {
        return clientId;
    }

    Availability disconnectAvailability() {
        return this.isConnected() ?
                Availability.available() :  Availability.unavailable("Not connected");
    }

    Availability connectAvailability() {
        return !this.isConnected() ?
                Availability.available() :  Availability.unavailable("Already connected");
    }

}
