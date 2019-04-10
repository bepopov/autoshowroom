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
    public Order save(Long clientId, OrderRequest orderRequest) {
        if (clientId != null) {
            Order order = new Order();
            Car car = new Car();
            Client client = clientRepository.findOneById(clientId);
            Model model = modelRepository.findOneById(orderRequest.getCar().getModel());
            List<Option> options = optionRepository.findAllByIds(orderRequest.getOptions());
            car.setOptions(options);
            car.setModel(model);
            order.setCar(car);
            order.setClient(client);
            order.setStatus(Status.NameEnum.INPROGRESS);
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Order change(Long clientId, Long orderId, OrderRequest orderRequest) {
        if (clientId != null) {
            Order order = orderRepository.findOneById(orderId);
            if (order == null) {
                return null;
            }
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
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Long delete(Long id) {
        orderRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrderByClientAndStatus(Long clientId, Status status) {
        if (status == null) {
            return orderRepository.findAllByClient(clientId);
        }
        return orderRepository.findAllByClientIdAndStatus(clientId, status.getName());
    }

    @Override
    public List<Order> getOrdersByClient(Long userId) {
        return orderRepository.findAllByClient(userId);
    }

}
