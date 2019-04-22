package ru.merann.bopopov.autoshowroom.server.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.merann.bopopov.autoshowroom.server.model.Make;
import ru.merann.bopopov.autoshowroom.server.repository.MakeRepository;
import ru.merann.bopopov.autoshowroom.server.service.MakeService;

import java.util.List;

@Service
public class MakeServiceImpl implements MakeService {

    private static final Logger logger = LogManager.getLogger(MakeServiceImpl.class);

    private MakeRepository makeRepository;

    public MakeServiceImpl(MakeRepository makeRepository) {
        this.makeRepository = makeRepository;
    }

    @Override
    public List<Make> searchByText(String text) {
        logger.log(Level.TRACE, String.format("Searching make with name like: %s", text));
        List<Make> makes = makeRepository.findAllNameByName(text);
        logger.log(Level.TRACE, String.format("Makes found: %s", makes == null ? makes : makes.toString()));
        return makes;
    }
}
