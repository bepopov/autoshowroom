package ru.merann.bopopov.autoshowroom.soapclient.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.ws.OrderSave;
import ru.merann.bopopov.autoshowroom.soapclient.service.ConnectionService;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OrderSaveConverter implements Converter<String, OrderSave> {

    private final Pattern pattern = Pattern.compile("(?<make>[A-Za-z]+) (?<model>[A-Za-z0-9]+)( (?<options>[А-Яа-я0-9 ]+( & [А-Яа-я0-9 ]+)*))?");
    private ConnectionService connectionService;

    public OrderSaveConverter(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Nullable
    @Override
    public OrderSave convert(String source) {
        Matcher matcher = pattern.matcher(source);
        OrderSave orderSave = new OrderSave();
        if (matcher.matches()) {
            orderSave.setModel(matcher.group("model"));
            String[] strings = matcher.group("options") == null ? new String[]{""} : matcher.group("options").split(" & ");
            orderSave.withOptions(Arrays.asList(strings));
            orderSave.setUsername(connectionService.getUsername());
        }
        return orderSave;
    }
}
