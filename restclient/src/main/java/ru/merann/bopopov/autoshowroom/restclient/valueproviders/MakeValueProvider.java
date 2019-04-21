package ru.merann.bopopov.autoshowroom.restclient.valueproviders;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.restclient.service.ValueProviderService;

import java.util.List;
import java.util.Objects;

@Component
public class MakeValueProvider implements ValueProvider {

    private ValueProviderService providerService;

    public MakeValueProvider(ValueProviderService providerService) {
        this.providerService = providerService;
    }

    @Override
    public boolean supports(MethodParameter parameter, CompletionContext completionContext) {
        return parameter.getParameterType().isAssignableFrom(String.class) && Objects.requireNonNull(parameter.getParameterName()).equals("make");
    }

    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext, String[] hints) {
        String text = completionContext.currentWordUpToCursor();
        return providerService.getMakes(text);
    }
}
