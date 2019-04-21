package ru.merann.bopopov.autoshowroom.restclient.service;

public interface ConnectionService {

    void connect(String username);

    boolean isConnected();

    String getUsername();

    void disconnect();

    Long getClientId();
}
