package ru.merann.bopopov.autoshowroom.restclient.service;

import ru.merann.bopopov.autoshowroom.restclient.model.*;

import java.util.List;

public interface ValueProviderWebService {

    List<Option> getOptions(String text);

    List<Make> getMakes(String text);

    List<Model> getModels(Long makeId, String text);

    List<Status> getStatuses();

    List<Order> getOrdersByClient(Long clientId);
}
