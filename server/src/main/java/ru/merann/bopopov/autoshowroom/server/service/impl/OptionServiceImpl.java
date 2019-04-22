package ru.merann.bopopov.autoshowroom.server.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.merann.bopopov.autoshowroom.server.model.Option;
import ru.merann.bopopov.autoshowroom.server.repository.OptionRepository;
import ru.merann.bopopov.autoshowroom.server.service.OptionService;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    private static final Logger logger = LogManager.getLogger(OptionServiceImpl.class);

    private OptionRepository optionRepository;

    public OptionServiceImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Override
    public List<Option> searchByText(String text) {
        logger.log(Level.TRACE, String.format("Searching options with name like: %s", text));
        List<Option> options = text == null ? optionRepository.findAll() : optionRepository.findAllByName(text);
        logger.log(Level.TRACE, String.format("Options found: %s", options == null ? null : options.toString()));
        return options;
    }
}
