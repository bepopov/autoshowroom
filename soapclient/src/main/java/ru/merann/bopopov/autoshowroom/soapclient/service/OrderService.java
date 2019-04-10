package ru.merann.bopopov.autoshowroom.soapclient.service;

import org.springframework.shell.table.Table;
import ru.merann.bopopov.autoshowroom.server.ws.OrderRequest;
import ru.merann.bopopov.autoshowroom.server.ws.Status;

import java.util.List;

public interface OrderService {
    void createOrder(OrderRequest orderSave);

    void editOrder(String order, String make, String model, List<Long> options);

    void deleteOrder(Long id);

    Table getOrders();

    Table getOrdersByStatus(Status status);
}
