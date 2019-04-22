package ru.merann.bopopov.autoshowroom.server.service;

import ru.merann.bopopov.autoshowroom.server.model.Client;

public interface ConnectionService {

    Long getClientId(String username);

    Client getClient(String username);

}
