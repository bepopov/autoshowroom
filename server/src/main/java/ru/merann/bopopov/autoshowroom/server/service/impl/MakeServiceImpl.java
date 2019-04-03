package ru.merann.bopopov.autoshowroom.server.service.impl;

import org.springframework.stereotype.Service;
import ru.merann.bopopov.autoshowroom.server.repository.MakeRepository;
import ru.merann.bopopov.autoshowroom.server.service.MakeService;

import java.util.List;

@Service
public class MakeServiceImpl implements MakeService {

    private MakeRepository makeRepository;

    public MakeServiceImpl(MakeRepository makeRepository) {
        this.makeRepository = makeRepository;
    }

    @Override
    public List<String> searchByText(String text) {
        return makeRepository.findAllNameByName(text);
    }
}
