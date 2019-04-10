package ru.merann.bopopov.autoshowroom.soapclient.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.ws.OrderRequest;
import ru.merann.bopopov.autoshowroom.server.ws.OrderRequestCar;
import ru.merann.bopopov.autoshowroom.soapclient.config.CommandPatterns;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OrderSaveConverter implements Converter<String, OrderRequest> {

    private final Pattern pattern = CommandPatterns.getOrderRequestPattern();
    private final Pattern idPattern = CommandPatterns.getIdPattern();

    @Nullable
    @Override
    public OrderRequest convert(String source) {
        Matcher matcher = pattern.matcher(source);
        OrderRequest orderRequest = new OrderRequest();
        if (matcher.matches()) {
            Long makelId = Long.valueOf(matcher.group("make"));
            Long modelId = Long.valueOf(matcher.group("model"));
            List<Long> options = new ArrayList<>();
            Matcher optionMatcher = idPattern.matcher(matcher.group("options"));
            while (optionMatcher.find()) {
                Long option = Long.valueOf(optionMatcher.group("id"));
                options.add(option);
            }
            OrderRequestCar car = new OrderRequestCar();
            car.setMake(makelId);
            car.setModel(modelId);
            orderRequest.setCar(car);
            orderRequest.withOptions(options);
        }
        return orderRequest;
    }
}
