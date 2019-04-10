package ru.merann.bopopov.autoshowroom.server.ws.impl;

import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.model.Order;
import ru.merann.bopopov.autoshowroom.server.model.OrderRequest;
import ru.merann.bopopov.autoshowroom.server.model.Status;
import ru.merann.bopopov.autoshowroom.server.service.OrderService;
import ru.merann.bopopov.autoshowroom.server.ws.OrderWebService;

import javax.jws.WebService;
import java.util.List;

@Component
@WebService(serviceName = "orderService", portName = "orderServicePort")
public class OrderWebServiceImpl implements OrderWebService {

    private OrderService orderService;

    public OrderWebServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Order save(Long clientId, OrderRequest orderRequest) {
        return orderService.save(clientId, orderRequest);
    }

    @Override
    public Order change(Long clientId, Long orderId, OrderRequest orderRequest) {
        return orderService.change(clientId, orderId, orderRequest);
    }

    @Override
    public Long delete(Long orderId) {
        orderService.delete(orderId);
        return orderId;
    }

    @Override
    public List<Order> getAll() {
        return orderService.getOrders();
    }

    @Override
    public List<Order> getAllByClientIdAndStatus(Long clientId, Status status) {
        return orderService.getOrderByClientAndStatus(clientId, status);
    }
}
