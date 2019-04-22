package ru.merann.bopopov.autoshowroom.restclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestClient {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RestClient.class);

        app.run(args);
        /*
        WebClientService clientService = new WebClientServiceImpl();
        OrderRequest orderRequest = new OrderRequest();
        OrderRequestCar car = new OrderRequestCar();
        car.setMake(1L);
        car.setModel(1L);
        orderRequest.setCar(car);
        List<Long> longs = new ArrayList<>();
        longs.add(1L);
        orderRequest.setOptions(longs);
        ResultListOrder listOrder = clientService.getOrdersByStatus(Status.ACCEPTED, 1L);
        System.out.println(listOrder.toString());
        */
    }

}
