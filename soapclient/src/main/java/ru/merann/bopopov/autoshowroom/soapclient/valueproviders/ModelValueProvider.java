package ru.merann.bopopov.autoshowroom.soapclient.valueproviders;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.soapclient.service.ValueProviderService;

import java.util.List;
import java.util.Objects;

@Component
public class ModelValueProvider implements ValueProvider {

    private ValueProviderService providerService;

    public ModelValueProvider(ValueProviderService providerService) {
        this.providerService = providerService;
    }

    @Override
    public boolean supports(MethodParameter parameter, CompletionContext completionContext) {
        return parameter.getParameterType().isAssignableFrom(String.class) && Objects.requireNonNull(parameter.getParameterName()).equals("model");
    }

    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext, String[] hints) {
        String text = completionContext.currentWordUpToCursor();
        String make = null;
        List<String> words = completionContext.getWords();
        int index = words.indexOf("--make");
        if (index + 1 < words.size()) {
            make = words.get(index + 1);
        }
        if (make != null) {
            return providerService.getModels(make, text, null);
        }
        return null;
    }
}
