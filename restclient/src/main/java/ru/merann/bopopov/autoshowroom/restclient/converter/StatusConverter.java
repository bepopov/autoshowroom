package ru.merann.bopopov.autoshowroom.restclient.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.restclient.model.Status;

@Component
public class StatusConverter implements Converter<String, Status> {
    @Override
    public Status convert(String source) {
        return Status.fromValue(source.toUpperCase());
    }
}
