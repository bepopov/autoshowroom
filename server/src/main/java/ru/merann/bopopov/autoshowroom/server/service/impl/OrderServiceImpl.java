package ru.merann.bopopov.autoshowroom.server.service.impl;

import org.springframework.stereotype.Service;
import ru.merann.bopopov.autoshowroom.server.model.*;
import ru.merann.bopopov.autoshowroom.server.repository.ClientRepository;
import ru.merann.bopopov.autoshowroom.server.repository.ModelRepository;
import ru.merann.bopopov.autoshowroom.server.repository.OptionRepository;
import ru.merann.bopopov.autoshowroom.server.repository.OrderRepository;
import ru.merann.bopopov.autoshowroom.server.service.OrderService;

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
    public Long save(OrderRequest orderRequest) {
        if (orderRequest.getClient() != null) {
            Order order = new Order();
            Car car = new Car();

            Client client = clientRepository.findOneById(orderRequest.getClient());
            Model model = modelRepository.findOneById(orderRequest.getCar().getModel());
            List<Option> options = optionRepository.findAllByIds(orderRequest.getOptions());
            car.setOptions(options);
            order.setCar(car);
            order.setClient(client);
            order.setStatus(Status.NameEnum.INPROGRESS);

            return orderRepository.save(order).getId();
        }
        return null;
    }

    @Override
    public void change(OrderRequest orderRequest) {
        if (orderRequest.getClient() != null) {
            Order order = orderRepository.findOneById(orderRequest.getOrder());
            Car car = new Car();
            if (orderRequest.getCar().getModel() != null) {
                Model model = modelRepository.findOneById(orderRequest.getCar().getModel());
                car.setModel(model);
            }
            else {
                car.setModel(order.getCar().getModel());
            }
            if (orderRequest.getOptions() == null || orderRequest.getOptions().size() == 0) {
                car.setOptions(order.getCar().getOptions());
            }
            else {
                List<Option> options = optionRepository.findAllByIds(orderRequest.getOptions());
                car.setOptions(options);
            }
            order.setCar(car);
            orderRepository.save(order);
        }
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

    @Override
    public List<Order> getOrdersByClient(Long userId) {
        return orderRepository.findAllByClient(userId);
    }

}
