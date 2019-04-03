package ru.merann.bopopov.autoshowroom.soapclient.service;

import ru.merann.bopopov.autoshowroom.server.ws.Model;

import java.util.List;

public interface ValueProviderService {

    List<String> getMakes(String text);

    List<Model> getModels(String make, String text);

    List<String> getOptions(String text);

    List<String> getStatuses();

}
