package ru.merann.bopopov.autoshowroom.server.service.impl;

import org.springframework.stereotype.Service;
import ru.merann.bopopov.autoshowroom.server.model.*;
import ru.merann.bopopov.autoshowroom.server.repository.ClientRepository;
import ru.merann.bopopov.autoshowroom.server.repository.ModelRepository;
import ru.merann.bopopov.autoshowroom.server.repository.OptionRepository;
import ru.merann.bopopov.autoshowroom.server.repository.OrderRepository;
import ru.merann.bopopov.autoshowroom.server.service.OrderService;
import ru.merann.bopopov.autoshowroom.server.ws.request.OrderChange;
import ru.merann.bopopov.autoshowroom.server.ws.request.OrderSave;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ModelRepository modelRepository;
    private OptionRepository optionRepository;
    private ClientRepository clientRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ModelRepository modelRepository, OptionRepository optionRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.modelRepository = modelRepository;
        this.optionRepository = optionRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Long save(OrderSave orderRequest) {
        Order order = new Order();
        Car car = new Car();

        Client client = clientRepository.findByName(orderRequest.getUsername());
        Model model = modelRepository.findOneByName(orderRequest.getModel());
        List<Option> options = optionRepository.findAllByNames(orderRequest.getOptions());

        car.setModel(model);
        car.setOptions(options);
        order.setCar(car);
        order.setClient(client);
        order.setStatus(Status.INPROGRESS);

        return orderRepository.save(order).getId();
    }

    @Override
    public void change(OrderChange orderRequest) {

        // TODO: Add validation for empty attributes

        Order order = orderRepository.findOneById(orderRequest.getOrderId());
        Car car = new Car();
        Model model = modelRepository.findOneByName(orderRequest.getModel());
        List<Option> options = optionRepository.findAllByNames(orderRequest.getOptions());
        car.setModel(model);
        car.setOptions(options);
        order.setCar(car);
        orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrderByClientAndStatus(String username, Status status) {
        return orderRepository.findAllByClientIdAndStatus(username, status);
    }

}
