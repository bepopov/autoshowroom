package ru.merann.bopopov.autoshowroom.soapclient.service.impl;

import org.springframework.shell.CompletionProposal;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.ws.*;
import ru.merann.bopopov.autoshowroom.soapclient.service.ConnectionService;
import ru.merann.bopopov.autoshowroom.soapclient.service.ValueProviderService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValueProviderServiceImpl implements ValueProviderService {

    private final ValueProviderWebService valueProviderWebService;
    private final ConnectionService connectionService;

    public ValueProviderServiceImpl(ConnectionService connectionService) {
        this.connectionService = connectionService;
        ru.merann.bopopov.autoshowroom.server.ws.impl.ValueProviderService providerService =
                new ru.merann.bopopov.autoshowroom.server.ws.impl.ValueProviderService();
        this.valueProviderWebService = providerService.getValueProviderServicePort();
    }

    @Override
    public List<CompletionProposal> getMakes(String text) {
        return valueProviderWebService.getMakes(text)
                .stream()
                .map(CompletionProposal::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompletionProposal> getModels(String make, String text, String parameterValue) {
        return valueProviderWebService.getModels(make, text).stream().map(model -> {
            String name = model.getName();
            String price = model.getPrice().toString();
            CompletionProposal proposal;
            if (parameterValue != null) {
                proposal = new CompletionProposal(parameterValue + name);
            } else {
                proposal = new CompletionProposal(name);
            }
            proposal.displayText(String.format("%s [Price: %s]", name, price));
            return proposal;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CompletionProposal> getOptions(String text, String parameterValue) {
        return valueProviderWebService.getOptions(text).stream().map(option -> {
            String name = option.getName();
            String price = option.getPrice().toString();
            CompletionProposal proposal;
            if (parameterValue != null) {
                proposal = new CompletionProposal(parameterValue + name);
            } else {
                proposal = new CompletionProposal(name);
            }
            proposal.displayText(String.format("%s [Price: %s]", name, price));
            return proposal;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CompletionProposal> getStatuses() {
        return valueProviderWebService.getStatuses()
                .stream()
                .map(Status::value)
                .collect(Collectors.toList())
                .stream()
                .map(CompletionProposal::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompletionProposal> getOrdersByClient() {
        return valueProviderWebService.getOrdersByClient(connectionService.getUsername()).stream().map(order -> {
            String id = order.getId().toString();
            String make = order.getCar().getModel().getMake().getName();
            String model = order.getCar().getModel().getName();
            String price = String.valueOf((order.getCar().getModel().getPrice() + (order.getCar().getOptions().size() == 0 ? 0 :
                    order.getCar().getOptions().stream().mapToLong(Option::getPrice).sum())));
            CompletionProposal proposal = new CompletionProposal(id);
            proposal.displayText(String.format("%s %s [Price: %s]", make, model, price));
            return proposal;
        }).collect(Collectors.toList());
    }
}
