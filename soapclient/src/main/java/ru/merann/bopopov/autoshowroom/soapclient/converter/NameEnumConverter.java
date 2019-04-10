package ru.merann.bopopov.autoshowroom.soapclient.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.ws.NameEnum;

@Component
public class NameEnumConverter implements Converter<String, NameEnum> {
    @Override
    public NameEnum convert(String source) {
        return NameEnum.fromValue(source.toUpperCase());
    }
}
