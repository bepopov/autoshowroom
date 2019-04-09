package ru.merann.bopopov.autoshowroom.server.rs.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.model.Status;
import ru.merann.bopopov.autoshowroom.server.rs.model.Car;
import ru.merann.bopopov.autoshowroom.server.rs.model.Option;
import ru.merann.bopopov.autoshowroom.server.rs.model.Order;
import ru.merann.bopopov.autoshowroom.server.rs.model.OrderRequest;
import ru.merann.bopopov.autoshowroom.server.rs.service.RestOrderService;
import ru.merann.bopopov.autoshowroom.server.service.OrderService;
import ru.merann.bopopov.autoshowroom.server.request.OrderChange;
import ru.merann.bopopov.autoshowroom.server.request.OrderSave;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestOrderServiceImpl implements RestOrderService {

    private OrderService orderService;

    public RestOrderServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void save(OrderRequest order) {
        orderService.save(convertToOrderSave(order));
    }

    @Override
    public void change(Integer id, OrderRequest order) {
        orderService.change(convertToOrderChange(id, order));
    }

    @Override
    public void delete(Integer id) {
        orderService.delete(id.longValue());
    }

    @Override
    public Order getOrder(Integer id) {
        return null;
    }

    @Override
    public List<Order> getOrders() {
        return convertToRestOrderList(orderService.getOrders());
    }

    @Override
    public List<Order> getOrderByClientAndStatus(String status, String username) {
        return convertToRestOrderList(orderService.getOrderByClientAndStatus(username, Status.valueOf(status)));
    }

    @Override
    public List<Order> getOrdersByClient(String username) {
        return null;
    }

    private OrderSave convertToOrderSave(OrderRequest orderRequest) {
        OrderSave orderSave = new OrderSave();
        orderSave.setModelId(orderRequest.getCar().getModel());
        orderSave.setUserId(orderRequest.getClient());
        orderSave.setOptions(orderRequest.getOptions());
        return orderSave;
    }

    private OrderChange convertToOrderChange(Integer id, OrderRequest orderRequest) {
        OrderChange orderChange = new OrderChange();
        orderChange.setOrderId(id.longValue());
        orderChange.setModelId(orderRequest.getCar().getModel());
        orderChange.setUserId(orderRequest.getClient());
        orderChange.setOptions(orderRequest.getOptions());
        return orderChange;
    }

    private List<Order> convertToRestOrderList(List<ru.merann.bopopov.autoshowroom.server.model.Order> orders) {
        return orders.stream().map(
                this::convertToRestOrder
        ).collect(Collectors.toList());
    }

    private Order convertToRestOrder(ru.merann.bopopov.autoshowroom.server.model.Order order) {
        Order restOrder = new Order();
        restOrder.setId(order.getId().intValue());
        restOrder.setClient(order.getClient().getName());
        restOrder.setStatus(order.getStatus().getName());
        restOrder.setOptions(convertToOptions(order.getCar().getOptions()));
        Car car = new Car();
        car.setMake(order.getCar().getModel().getMake().getName());
        car.setModel(order.getCar().getModel().getName());
        car.setPrice(order.getCar().getModel().getPrice().intValue());
        restOrder.setCar(car);
        return restOrder;
    }

    private List<Option> convertToOptions(List<ru.merann.bopopov.autoshowroom.server.model.Option> options) {
        return options.stream().map(
                o -> {
                    Option option = new Option();
                    option.setName(o.getName());
                    option.setPrice(o.getPrice().intValue());
                    return option;
                }).collect(Collectors.toList());
    }
}