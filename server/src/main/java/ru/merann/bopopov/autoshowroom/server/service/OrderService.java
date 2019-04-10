package ru.merann.bopopov.autoshowroom.server.service;

import ru.merann.bopopov.autoshowroom.server.model.Order;
import ru.merann.bopopov.autoshowroom.server.model.OrderRequest;
import ru.merann.bopopov.autoshowroom.server.model.Status;

import java.util.List;

public interface OrderService {

    Order save(Long clientId, OrderRequest order);

    Order change(Long clientId, Long orderId, OrderRequest order);

    Long delete(Long id);

    List<Order> getOrders();

    List<Order> getOrderByClientAndStatus(Long clientId, Status status);

    List<Order> getOrdersByClient(Long userId);
}
