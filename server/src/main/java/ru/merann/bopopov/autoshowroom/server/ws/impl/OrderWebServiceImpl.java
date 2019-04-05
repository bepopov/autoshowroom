package ru.merann.bopopov.autoshowroom.server.ws.impl;

import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.model.Order;
import ru.merann.bopopov.autoshowroom.server.model.Status;
import ru.merann.bopopov.autoshowroom.server.service.OrderService;
import ru.merann.bopopov.autoshowroom.server.ws.request.OrderChange;
import ru.merann.bopopov.autoshowroom.server.ws.request.OrderSave;
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
    public Long save(OrderSave orderRequest) {
        return orderService.save(orderRequest);
    }

    @Override
    public String change(OrderChange order) {
        orderService.change(order);
        return "Success";
    }

    @Override
    public String delete(Long orderId) {
        orderService.delete(orderId);
        return "Success";
    }

    @Override
    public List<Order> getAll() {
        return orderService.getOrders();
    }

    @Override
    public List<Order> getAllByClientIdAndStatus(String username, Status status) {
        return orderService.getOrderByClientAndStatus(username, status);
    }
}
