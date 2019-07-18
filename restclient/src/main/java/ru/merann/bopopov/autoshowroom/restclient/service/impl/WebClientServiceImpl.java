package ru.merann.bopopov.autoshowroom.restclient.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:application.properties")
public class WebClientServiceImpl implements WebClientService {

    private static final String MIME_TYPE = "application/json";
    private static final String USER_AGENT = "Order Service";

    private final String JAVA_SERVER;
    private final String GROOVY_SERVER;
    private final Boolean groovyServerEnabled;

    private final WebClient javaServer;
    private final WebClient groovyServer;

    public WebClientServiceImpl(@Value("${java_server.url}") String JAVA_SERVER,
                                @Value("${groovy_server.url}") String GROOVY_SERVER,
                                @Value("#{new Boolean('${groovy_server.enabled}')}") boolean groovyServerEnabled) {
        this.JAVA_SERVER = JAVA_SERVER;
        this.GROOVY_SERVER = GROOVY_SERVER;
        this.groovyServerEnabled = groovyServerEnabled;
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
                .body(Mono.just(orderRequest), OrderRequest.class);
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
