package ru.merann.bopopov.autoshowroom.soapclient.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.ws.Status;

@Component
public class StatusConverter implements Converter<String, Status> {
    @Override
    public Status convert(String source) {
        return Status.fromValue(source.toUpperCase());
    }
}
