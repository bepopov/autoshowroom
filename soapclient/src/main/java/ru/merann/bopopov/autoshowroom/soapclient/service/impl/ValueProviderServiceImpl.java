package ru.merann.bopopov.autoshowroom.soapclient.service.impl;

import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.ws.Model;
import ru.merann.bopopov.autoshowroom.server.ws.Status;
import ru.merann.bopopov.autoshowroom.server.ws.ValueProviderWebService;
import ru.merann.bopopov.autoshowroom.soapclient.service.ConsoleService;
import ru.merann.bopopov.autoshowroom.soapclient.service.ValueProviderService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValueProviderServiceImpl implements ValueProviderService {

    private final ValueProviderWebService valueProviderWebService;
    private String make;
    private ConsoleService consoleService;

    public ValueProviderServiceImpl(ConsoleService consoleService) {
        this.consoleService = consoleService;
        ru.merann.bopopov.autoshowroom.server.ws.impl.ValueProviderService providerService =
                new ru.merann.bopopov.autoshowroom.server.ws.impl.ValueProviderService();
        this.valueProviderWebService = providerService.getValueProviderServicePort();
    }

    @Override
    public List<String> getMakes(String text) {
        return valueProviderWebService.getMakes(text);
    }

    @Override
    public List<Model> getModels(String make, String text) {
        return valueProviderWebService.getModels(make, text);
    }

    @Override
    public List<String> getOptions(String text) {
        return valueProviderWebService.getOptions(text)
                .stream()
                .map(option -> option.getName() + " [Price:" + option.getPrice() + "]")
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getStatuses() {
        return valueProviderWebService.getStatuses()
                .stream()
                .map(Status::value)
                .collect(Collectors.toList());
    }
}
