package ru.merann.bopopov.autoshowroom.server.ws.impl;

import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.model.Model;
import ru.merann.bopopov.autoshowroom.server.model.Option;
import ru.merann.bopopov.autoshowroom.server.model.Order;
import ru.merann.bopopov.autoshowroom.server.model.Status;
import ru.merann.bopopov.autoshowroom.server.service.MakeService;
import ru.merann.bopopov.autoshowroom.server.service.ModelService;
import ru.merann.bopopov.autoshowroom.server.service.OptionService;
import ru.merann.bopopov.autoshowroom.server.service.OrderService;
import ru.merann.bopopov.autoshowroom.server.ws.ValueProviderWebService;

import javax.jws.WebService;
import java.util.Arrays;
import java.util.List;

@Component
@WebService(serviceName = "valueProviderService", portName = "valueProviderServicePort")
public class ValueProviderWebServiceImpl implements ValueProviderWebService {

    private MakeService makeService;
    private ModelService modelService;
    private OptionService optionService;
    private OrderService orderService;

    public ValueProviderWebServiceImpl(MakeService makeService, ModelService modelService, OptionService optionService, OrderService orderService) {
        this.makeService = makeService;
        this.modelService = modelService;
        this.optionService = optionService;
        this.orderService = orderService;
    }

    @Override
    public List<String> getMakes(String text) {
        return makeService.searchByText(text);
    }

    @Override
    public List<Model> getModels(String make, String text) {
        return modelService.searchByText(make, text);
    }

    @Override
    public List<Option> getOptions(String text) {
        return optionService.searchByText(text);
    }

    @Override
    public List<Status> getStatuses() {
        return Arrays.asList(Status.values());
    }

    @Override
    public List<Order> getOrdersByClient(String username) {
        return orderService.getOrdersByClient(username);
    }
}
