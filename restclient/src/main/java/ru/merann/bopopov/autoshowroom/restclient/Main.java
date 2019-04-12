package ru.merann.bopopov.autoshowroom.restclient;

import reactor.core.publisher.Mono;
import ru.merann.bopopov.autoshowroom.restclient.model.*;
import ru.merann.bopopov.autoshowroom.restclient.service.WebClientService;
import ru.merann.bopopov.autoshowroom.restclient.service.impl.WebClientServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        WebClientService clientService = new WebClientServiceImpl();
        OrderRequest orderRequest = new OrderRequest();
        OrderRequestCar car = new OrderRequestCar();
        car.setMake(1L);
        car.setModel(1L);
        orderRequest.setCar(car);
        List<Long> longs = new ArrayList<>();
        longs.add(1L);
        orderRequest.setOptions(longs);
        ResultListOrder listOrder = clientService.getOrdersByStatus(null, 1L);
        System.out.println(listOrder.toString());
    }

}
