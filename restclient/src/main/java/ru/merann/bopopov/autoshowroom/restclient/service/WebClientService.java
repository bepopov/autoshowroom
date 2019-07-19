package ru.merann.bopopov.autoshowroom.restclient.service;

import ru.merann.bopopov.autoshowroom.restclient.model.Order;
import ru.merann.bopopov.autoshowroom.restclient.model.OrderRequest;
import ru.merann.bopopov.autoshowroom.restclient.model.ResultListOrder;
import ru.merann.bopopov.autoshowroom.restclient.model.Status;

public interface WebClientService {

    Long createOrder(OrderRequest orderRequest, Long userId);

    Order editOrder(OrderRequest orderRequest, Long userId, Long orderId);

    Long deleteOrder(Long orderId, Long userId);

    ResultListOrder getOrders();

    ResultListOrder getOrdersByStatus(Status status, Long userId);

}
