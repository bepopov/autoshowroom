package ru.merann.bopopov.autoshowroom.restclient.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.merann.bopopov.autoshowroom.restclient.model.Order;
import ru.merann.bopopov.autoshowroom.restclient.model.OrderRequest;
import ru.merann.bopopov.autoshowroom.restclient.model.ResultListOrder;
import ru.merann.bopopov.autoshowroom.restclient.model.Status;
import ru.merann.bopopov.autoshowroom.restclient.service.WebClientService;

@Service
public class WebClientServiceImpl implements WebClientService {

    private static final String MIME_TYPE = "application/json";
    private static final String API_BASE_URL = "http://localhost:9000";
    private static final String USER_AGENT = "Spring 5 WebClient";

    private final WebClient webClient;

    public WebClientServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .build();
    }


    @Override
    public Order createOrder(OrderRequest orderRequest, Long userId) {
        return webClient.post().uri("/clients/" + userId+ "/orders")
                .body(Mono.just(orderRequest), OrderRequest.class)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
    }

    @Override
    public Order editOrder(OrderRequest orderRequest, Long userId, Long orderId) {
        return webClient.put()
                .uri("/clients/" + userId+ "/orders/" + orderId)
                .body(Mono.just(orderRequest), OrderRequest.class)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
    }

    @Override
    public Long deleteOrder(Long orderId, Long userId) {
        return webClient.delete()
                .uri("/clients/" + userId + "/orders/" + orderId)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }

    @Override
    public ResultListOrder getOrders() {
        return webClient.get()
                .uri("/orders")
                .retrieve()
                .bodyToMono(ResultListOrder.class)
                .block();
    }

    @Override
    public ResultListOrder getOrdersByStatus(Status status, Long userId) {
        if (status != null) {
            return webClient.get()
                    .uri("/clients/" + userId + "/orders?status=" + status)
                    .retrieve()
                    .bodyToMono(ResultListOrder.class)
                    .block();
        }
        else {
            return webClient.get()
                    .uri("/clients/" + userId + "/orders")
                    .retrieve()
                    .bodyToMono(ResultListOrder.class)
                    .block();
        }
    }
}
