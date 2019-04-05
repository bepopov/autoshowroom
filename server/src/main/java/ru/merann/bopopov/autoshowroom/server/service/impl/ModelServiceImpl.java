package ru.merann.bopopov.autoshowroom.server.service.impl;

import org.springframework.stereotype.Service;
import ru.merann.bopopov.autoshowroom.server.model.Model;
import ru.merann.bopopov.autoshowroom.server.repository.ModelRepository;
import ru.merann.bopopov.autoshowroom.server.service.ModelService;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Model> searchByText(String make, String text) {
        return modelRepository.findAllByName(make, text);
    }
}
