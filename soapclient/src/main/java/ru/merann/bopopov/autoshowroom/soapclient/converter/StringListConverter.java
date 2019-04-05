package ru.merann.bopopov.autoshowroom.soapclient.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StringListConverter implements Converter<String, List<String>> {

    private final Pattern pattern = Pattern.compile("(?<list>[A-Za-zА-Яа-я0-9 ]+( AND [A-Za-zА-Яа-я0-9 ]+)*)");

    @Override
    public List<String> convert(String source) {
        Matcher matcher = pattern.matcher(source);
        if (matcher.matches()) {
            String string = matcher.group("list");
            String [] strings = string.split(" AND ");
            return Arrays.asList(strings);
        }
        return null;
    }
}
