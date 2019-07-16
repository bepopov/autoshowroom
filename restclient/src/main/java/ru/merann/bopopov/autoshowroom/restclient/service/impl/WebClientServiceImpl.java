package ru.merann.bopopov.autoshowroom.restclient.service.impl;

import org.springframework.beans.factory.annotation.Value;
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
    private static final String USER_AGENT = "Order Service";
    
    @Value("java_server.url")
    private static String JAVA_SERVER = "";
    @Value("groovy_server.url")
    private static String GROOVY_SERVER = "";
    @Value("groovy_server.enabled")
    private static boolean groovyServerEnabled = false;

    private final WebClient javaServer;
    private final WebClient groovyServer;

    public WebClientServiceImpl() {
        this.javaServer = WebClient.builder()
                .baseUrl(JAVA_SERVER)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .build();
        if (groovyServerEnabled) {
            groovyServer = WebClient.builder()
                    .baseUrl(GROOVY_SERVER)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MIME_TYPE)
                    .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                    .build();
        }
        else {
            groovyServer = null;
        }
    }


    @Override
    public Order createOrder(OrderRequest orderRequest, Long userId) {
        groovyServer.post().uri("/clients/" + userId + "/orders")
                .body(Mono.just(orderRequest), OrderRequest.class)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        return javaServer.post().uri("/clients/" + userId+ "/orders")
                .body(Mono.just(orderRequest), OrderRequest.class)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
    }

    @Override
    public Order editOrder(OrderRequest orderRequest, Long userId, Long orderId) {
        return javaServer.put()
                .uri("/clients/" + userId+ "/orders/" + orderId)
                .body(Mono.just(orderRequest), OrderRequest.class)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
    }

    @Override
    public Long deleteOrder(Long orderId, Long userId) {
        return javaServer.delete()
                .uri("/clients/" + userId + "/orders/" + orderId)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }

    @Override
    public ResultListOrder getOrders() {
        return javaServer.get()
                .uri("/orders")
                .retrieve()
                .bodyToMono(ResultListOrder.class)
                .block();
    }

    @Override
    public ResultListOrder getOrdersByStatus(Status status, Long userId) {
        if (status != null) {
            return javaServer.get()
                    .uri("/clients/" + userId + "/orders?status=" + status)
                    .retrieve()
                    .bodyToMono(ResultListOrder.class)
                    .block();
        }
        else {
            return javaServer.get()
                    .uri("/clients/" + userId + "/orders")
                    .retrieve()
                    .bodyToMono(ResultListOrder.class)
                    .block();
        }
    }
}
