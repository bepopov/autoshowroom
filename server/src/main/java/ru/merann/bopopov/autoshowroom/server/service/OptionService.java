package ru.merann.bopopov.autoshowroom.server.service;

import ru.merann.bopopov.autoshowroom.server.model.Option;

import java.util.List;

public interface OptionService {

    List<Option> searchByText(String text);

}
