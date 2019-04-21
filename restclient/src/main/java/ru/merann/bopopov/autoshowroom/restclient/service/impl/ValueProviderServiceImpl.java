package ru.merann.bopopov.autoshowroom.restclient.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.shell.CompletionProposal;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.restclient.config.CommandPatterns;
import ru.merann.bopopov.autoshowroom.restclient.service.ConnectionService;
import ru.merann.bopopov.autoshowroom.restclient.service.ValueProviderService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class ValueProviderServiceImpl implements ValueProviderService {

    private final ValueProviderWebService valueProviderWebService;
    private final ConnectionService connectionService;
    private final Pattern idPattern = CommandPatterns.getIdPattern();
    private static final Logger LOGGER = LogManager.getLogger(ValueProviderServiceImpl.class);

    public ValueProviderServiceImpl(ConnectionService connectionService) {
        this.connectionService = connectionService;
        ru.merann.bopopov.autoshowroom.server.ws.impl.ValueProviderService providerService =
                new ru.merann.bopopov.autoshowroom.server.ws.impl.ValueProviderService();
        this.valueProviderWebService = providerService.getValueProviderServicePort();
    }

    @Override
    public List<CompletionProposal> getMakes(String text) {
        LOGGER.log(Level.TRACE, "Get makes for completion");
        List<Make> makes = valueProviderWebService.getMakes(text);
        LOGGER.log(Level.TRACE, String.format("--- MAKES: %s", makes.toString()));
        return makes
                .stream()
                .map(make -> new CompletionProposal(String.format("%s(%s)", make.getName(), make.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<CompletionProposal> getModels(String make, String text, String parameterValue) {
        LOGGER.log(Level.TRACE, "Get models for completion");
        Matcher matcher = idPattern.matcher(make);
        if (matcher.find()) {
            Long makeId = Long.valueOf(matcher.group("id"));
            List<Model> models = valueProviderWebService.getModels(makeId, text);
            LOGGER.log(Level.TRACE, String.format("--- MODELS: %s", models.toString()));
            return models.stream().map(model -> {
                String name = model.getName();
                String price = model.getPrice().toString();
                CompletionProposal proposal;
                if (parameterValue != null) {
                    proposal = new CompletionProposal(parameterValue + name + "(" + model.getId() + ")");
                } else {
                    proposal = new CompletionProposal(String.format("%s(%s)", name, model.getId()));
                }
                proposal.displayText(String.format("%s [Price: %s]", name, price));
                return proposal;
            }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<CompletionProposal> getOptions(String text, String parameterValue) {
        LOGGER.log(Level.TRACE, "Get options for completion");
        List<Option> options = valueProviderWebService.getOptions(text);
        LOGGER.log(Level.TRACE, String.format("--- OPTIONS: %s", options.toString()));
        return options.stream().map(option -> {
            String name = option.getName();
            String price = option.getPrice().toString();
            CompletionProposal proposal;
            if (parameterValue != null) {
                proposal = new CompletionProposal(parameterValue + name + "(" + option.getId() + ")");
            } else {
                proposal = new CompletionProposal(String.format("%s(%s)", name, option.getId()));
            }
            proposal.displayText(String.format("%s [Price: %s]", name, price));
            return proposal;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CompletionProposal> getStatuses() {
        LOGGER.log(Level.TRACE, "Get statuses for completion");
        List<Status> statuses = valueProviderWebService.getStatuses();
        LOGGER.log(Level.TRACE, String.format("--- STATUSES: %s", statuses.toString()));
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
        LOGGER.log(Level.TRACE, "Get orders for completion");
        List<Order> orders = valueProviderWebService.getOrdersByClient(connectionService.getClientId());
        LOGGER.log(Level.TRACE, String.format("--- ORDERS: %s", orders.toString()));
        return orders.stream().map(order -> {
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
