package ru.merann.bopopov.autoshowroom.server.service;

import ru.merann.bopopov.autoshowroom.server.model.Model;

import java.util.List;

public interface ModelService {

    List<Model> searchByText(Long makeId, String text);

}
