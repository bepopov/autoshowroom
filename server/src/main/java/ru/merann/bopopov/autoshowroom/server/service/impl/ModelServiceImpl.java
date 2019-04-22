package ru.merann.bopopov.autoshowroom.server.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.merann.bopopov.autoshowroom.server.model.Model;
import ru.merann.bopopov.autoshowroom.server.repository.ModelRepository;
import ru.merann.bopopov.autoshowroom.server.service.ModelService;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private static final Logger logger = LogManager.getLogger(ModelServiceImpl.class);

    private ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Model> searchByText(Long makeId, String text) {
        logger.log(Level.TRACE, String.format("Searching model with name like: %s", text));
        List<Model> models = modelRepository.findAllByName(makeId, text);
        logger.log(Level.TRACE, String.format("Models found: %s", models == null ? null : models.toString()));
        return models;
    }
}
