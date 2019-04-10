package ru.merann.bopopov.autoshowroom.server.service.impl;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.model.Status;


@Component
public class StringToStatusConverter implements Converter<String, Status> {

    @Override
    public Status convert(String source) {
        if (source == null || source.equals("null")) {
            return null;
        }
        Status status = new Status();
        status.setName(Status.NameEnum.valueOf(source));
        return status;
    }
}
