package ru.merann.bopopov.autoshowroom.server.service.impl;

import org.springframework.stereotype.Service;
import ru.merann.bopopov.autoshowroom.server.model.Option;
import ru.merann.bopopov.autoshowroom.server.repository.OptionRepository;
import ru.merann.bopopov.autoshowroom.server.service.OptionService;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    private OptionRepository optionRepository;

    public OptionServiceImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Override
    public List<Option> searchByText(String text) {
        if (text == null) {
            return optionRepository.findAll();
        }
        return optionRepository.findAllByName(text);
    }
}
