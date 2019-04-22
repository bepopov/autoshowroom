package ru.merann.bopopov.autoshowroom.restclient.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.merann.bopopov.autoshowroom.restclient.model.*;
import ru.merann.bopopov.autoshowroom.restclient.service.ValueProviderWebService;

import java.util.List;
import java.util.Objects;

@Component
public class ValueProviderWebServiceImpl implements ValueProviderWebService {

    private static final String MIME_TYPE = "application/json";
    private static final String API_BASE_URL = "http://localhost:9000";
    private static final String USER_AGENT = "Value Provider";

    private final WebClient webClient;

    public ValueProviderWebServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .build();
    }

    @Override
    public List<Option> getOptions(String text) {
        return Objects.requireNonNull(webClient.get()
                .uri("/options?searchText=" + text)
                .retrieve()
                .bodyToMono(ResultListOption.class)
                .block()).getItems();
    }

    @Override
    public List<Make> getMakes(String text) {
        return Objects.requireNonNull(webClient.get()
                .uri("/makes?searchText=" + text)
                .retrieve()
                .bodyToMono(ResultListMake.class)
                .block()).getItems();
    }

    @Override
    public List<Model> getModels(Long makeId, String text) {
        return Objects.requireNonNull(webClient.get()
                .uri("/makes/" + makeId + "/models?searchText=" + text)
                .retrieve()
                .bodyToMono(ResultListModel.class)
                .block()).getItems();
    }

    @Override
    public List<Status> getStatuses() {
        return Objects.requireNonNull(webClient.get()
                .uri("/statuses")
                .retrieve()
                .bodyToMono(ResultListStatus.class)
                .block()).getItems();
    }

    @Override
    public List<Order> getOrdersByClient(Long clientId) {
        return Objects.requireNonNull(webClient.get()
                .uri("/clients/" + clientId + "/orders")
                .retrieve()
                .bodyToMono(ResultListOrder.class)
                .block()).getItems();
    }
}
