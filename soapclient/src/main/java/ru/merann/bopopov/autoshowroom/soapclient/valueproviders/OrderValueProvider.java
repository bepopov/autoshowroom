package ru.merann.bopopov.autoshowroom.soapclient.valueproviders;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.soapclient.service.ValueProviderService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OrderValueProvider implements ValueProvider {

    private ValueProviderService providerService;
    private final Pattern pattern = Pattern.compile("(?<make>[A-Za-z]+) (?<model>[A-Za-z0-9]+)?( (?<options>[А-Яа-я0-9 ]+( & [А-Яа-я0-9 ]+)*))?");

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
            return Objects.equals(parameter.getParameterName(), "orderSave") && previousWord.equals("--order") || currentWord.contains(" & ");
        }
        return false;
    }

    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext, String[] hints) {
        List<String> words = completionContext.getWords();
        String text = completionContext.currentWordUpToCursor();
        if (words.size() == 2) {
            if (words.get(1).equals("")) {
                return providerService.getMakes(text);
            }
            Matcher matcher = pattern.matcher(words.get(1));
            if (matcher.matches()) {
                String make = matcher.group("make");
                String model = matcher.group("model");
                String options = matcher.group("options");
                if (make == null) {
                    return providerService.getMakes(make);
                }
                else if (model == null) {
                    return providerService.getModels(make, "");
                }
                else if (options == null) {
                    return providerService.getOptions("");
                }
            }
        }
        return null;
    }
}
