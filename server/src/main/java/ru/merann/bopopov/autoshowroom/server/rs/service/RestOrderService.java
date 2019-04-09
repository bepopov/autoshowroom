package ru.merann.bopopov.autoshowroom.server.rs.service;

import ru.merann.bopopov.autoshowroom.server.rs.model.Order;
import ru.merann.bopopov.autoshowroom.server.rs.model.OrderRequest;

import java.util.List;

public interface RestOrderService {

    void save(OrderRequest order);

    void change(Integer id, OrderRequest order);

    void delete(Integer id);

    Order getOrder(Integer id);

    List<Order> getOrders();

    List<Order> getOrderByClientAndStatus(String status, String username);

    List<Order> getOrdersByClient(String username);

}
