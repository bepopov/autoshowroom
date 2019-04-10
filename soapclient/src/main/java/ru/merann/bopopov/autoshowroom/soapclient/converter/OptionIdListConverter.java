package ru.merann.bopopov.autoshowroom.soapclient.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.soapclient.config.CommandPatterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OptionIdListConverter implements Converter<String, List<Long>> {

    private final Pattern pattern = CommandPatterns.getOptionPattern();
    private final Pattern idPattern = CommandPatterns.getIdPattern();

    @Override
    public List<Long> convert(String source) {
        Matcher matcher = pattern.matcher(source);
        List<Long> options = new ArrayList<>();
        if (matcher.matches()) {
            Matcher optionMatcher = idPattern.matcher(matcher.group("options"));
            while (optionMatcher.find()) {
                Long option = Long.valueOf(optionMatcher.group("id"));
                options.add(option);
            }
        }
        return options;
    }
}
