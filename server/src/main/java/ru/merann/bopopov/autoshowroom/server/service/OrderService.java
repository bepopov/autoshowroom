package ru.merann.bopopov.autoshowroom.server.service;

import ru.merann.bopopov.autoshowroom.server.model.Order;
import ru.merann.bopopov.autoshowroom.server.model.OrderRequest;
import ru.merann.bopopov.autoshowroom.server.model.Status;

import java.util.List;

public interface OrderService {

    Long save(OrderRequest order);

    void change(OrderRequest order);

    void delete(Long id);

    List<Order> getOrders();

    List<Order> getOrderByClientAndStatus(String username, Status status);

    List<Order> getOrdersByClient(Long userId);
}
