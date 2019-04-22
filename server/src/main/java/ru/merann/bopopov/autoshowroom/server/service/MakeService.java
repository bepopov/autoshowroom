package ru.merann.bopopov.autoshowroom.server.service;

import ru.merann.bopopov.autoshowroom.server.model.Make;

import java.util.List;

public interface MakeService {

    List<Make> searchByText(String text);

}
