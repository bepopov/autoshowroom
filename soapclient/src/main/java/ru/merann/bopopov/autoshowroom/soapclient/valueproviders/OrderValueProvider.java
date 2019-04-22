package ru.merann.bopopov.autoshowroom.soapclient.valueproviders;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.soapclient.config.CommandPatterns;
import ru.merann.bopopov.autoshowroom.soapclient.service.ValueProviderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OrderValueProvider implements ValueProvider {

    private ValueProviderService providerService;
    private final Pattern pattern = CommandPatterns.getOrderRequestTextPattern();

    public OrderValueProvider(ValueProviderService providerService) {
        this.providerService = providerService;
    }

    @Override
    public boolean supports(MethodParameter parameter, CompletionContext completionContext) {
        List<String> words = completionContext.getWords();
        int size = words.size();
        if (size > 1) {
            String previousWord = words.get(size - 2);
            String currentWord = words.get(size - 1);
            return Objects.equals(parameter.getParameterName(), "orderRequest") && previousWord.equals("--order");
        }
        return false;
    }

    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext, String[] hints) {
        List<String> words = completionContext.getWords();
            String text = completionContext.currentWordUpToCursor();
        if (words.size() == 2) {
            String parameterValue = words.get(1);
            long countSpaces = parameterValue.chars().filter(ch -> ch == ' ').count();
            if (parameterValue.equals("") || countSpaces == 0) {
                return providerService.getMakes(text);
            }
            Matcher matcher = pattern.matcher(words.get(1));
            if (matcher.matches()) {
                String make = matcher.group("make");
                String model = matcher.group("model");
                String options = matcher.group("options");
                if (countSpaces == 1) {
                    if (model == null) {
                        model = "";
                    }
                    return providerService.getModels(make, model, make + " ");
                } else if (countSpaces > 1) {
                    if (options == null) {
                        return providerService.getOptions("", make + " " + model + " ");
                    } else {
                        if (!options.contains(" AND ")) {
                            return providerService.getOptions(options, make + " " + model + " ");
                        } else {
                            int countAnds = StringUtils.countMatches(options, " AND");
                            String[] strings = options.split(" AND( )?");
                            if (strings.length <= countAnds) {
                                return providerService.getOptions("", parameterValue);
                            } else {
                                String lastWord = strings[strings.length - 1];
                                return providerService.getOptions(lastWord, StringUtils.remove(parameterValue, lastWord));
                            }
                        }
                    }
                }
            }
        }
        return new ArrayList<>();
    }
}
