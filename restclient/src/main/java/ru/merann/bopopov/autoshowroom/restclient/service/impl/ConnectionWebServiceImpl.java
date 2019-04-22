package ru.merann.bopopov.autoshowroom.restclient.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.merann.bopopov.autoshowroom.restclient.model.Client;
import ru.merann.bopopov.autoshowroom.restclient.service.ConnectionWebService;

import java.util.Objects;

@Component
public class ConnectionWebServiceImpl implements ConnectionWebService {

    private static final String MIME_TYPE = "application/json";
    private static final String API_BASE_URL = "http://localhost:9000";
    private static final String USER_AGENT = "Connection Service";

    private final WebClient webClient;

    public ConnectionWebServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .build();
    }

    @Override
    public Long connect(String username) {
        return Objects.requireNonNull(webClient.get()
                .uri("/clients/" + username)
                .retrieve()
                .bodyToMono(Client.class)
                .block()).getId();
    }
}
