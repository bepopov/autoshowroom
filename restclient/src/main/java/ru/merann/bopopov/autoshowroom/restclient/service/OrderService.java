package ru.merann.bopopov.autoshowroom.restclient.service;

import org.springframework.shell.table.Table;
import ru.merann.bopopov.autoshowroom.restclient.model.OrderRequest;
import ru.merann.bopopov.autoshowroom.restclient.model.Status;

import java.util.List;

public interface OrderService {
    void createOrder(OrderRequest orderSave);

    void editOrder(String order, String make, String model, List<Long> options);

    void deleteOrder(Long id);

    Table getOrders();

    Table getOrdersByStatus(Status status);
}
