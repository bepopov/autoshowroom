package ru.merann.bopopov.autoshowroom.server.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.server.model.Status;


@Component
public class StringToStatusConverter implements Converter<String, Status> {

    private static final Logger logger = LogManager.getLogger(StringToStatusConverter.class);

    @Override
    public Status convert(String source) {
        if (source == null || source.equals("null")) {
            logger.log(Level.TRACE, "Unable to convert to Status object because source is null");
            return null;
        }
        logger.log(Level.TRACE, String.format("Converting to Status. Source: %s", source));
        return Status.valueOf(source);
    }
}
