package ru.merann.bopopov.autoshowroom.server.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

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
        logger.info(String.format("Saving order: %s", orderRequest.toString()));
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
            order.setStatus(Status.INPROGRESS);
            return orderRepository.save(order);
        }
        logger.error("Client id is null");
        return null;
    }

    @Override
    public Order change(Long clientId, Long orderId, OrderRequest orderRequest) {
        logger.info(String.format("Changing order: %s", orderRequest.toString()));
        if (clientId != null) {
            Order order = orderRepository.findOneById(orderId);
            if (order == null) {
                logger.error("Order is null");
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
            logger.info(String.format("Order's car is: %s", car.toString()));
            if (orderRequest.getOptions() == null || orderRequest.getOptions().size() == 0) {
                car.setOptions(order.getCar().getOptions());
            }
            else {
                List<Option> options = optionRepository.findAllByIds(orderRequest.getOptions());
                car.setOptions(options);
            }
            logger.info(String.format("Order's options are: %s", car.getOptions()));
            order.setCar(car);
            return orderRepository.save(order);
        }
        logger.error("Client id is null");
        return null;
    }

    @Override
    public Long delete(Long id) {
        logger.info(String.format("Deleting order with ID: %s", id));
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
            logger.info(String.format("Get all orders for client: %s", clientId));
            return orderRepository.findAllByClient(clientId);
        }
        logger.info(String.format("Get all orders for client: %s", clientId));
        return orderRepository.findAllByClientIdAndStatus(clientId, status);
    }

    @Override
    public List<Order> getOrdersByClient(Long userId) {
        return orderRepository.findAllByClient(userId);
    }

}
