package ru.merann.bopopov.autoshowroom.server.service;

import ru.merann.bopopov.autoshowroom.server.model.Order;
import ru.merann.bopopov.autoshowroom.server.model.Status;
import ru.merann.bopopov.autoshowroom.server.request.OrderChange;
import ru.merann.bopopov.autoshowroom.server.request.OrderSave;

import java.util.List;

public interface OrderService {

    Long save(OrderSave order);

    void change(OrderChange order);

    void delete(Long id);

    List<Order> getOrders();

    List<Order> getOrderByClientAndStatus(String username, Status status);

    List<Order> getOrdersByClient(String username);
}
