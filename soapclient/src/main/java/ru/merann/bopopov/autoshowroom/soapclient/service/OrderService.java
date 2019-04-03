package ru.merann.bopopov.autoshowroom.soapclient.service;

import org.springframework.shell.table.Table;
import ru.merann.bopopov.autoshowroom.server.ws.OrderSave;
import ru.merann.bopopov.autoshowroom.server.ws.Status;

import java.util.List;

public interface OrderService {
    void createOrder(OrderSave orderSave);

    void editOrder(String order, String make, String model, List<String> options);

    void deleteOrder(Long id);

    Table getOrders();

    Table getOrdersByStatus(Status status);
}
