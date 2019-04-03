package ru.merann.bopopov.autoshowroom.soapclient.valueproviders;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;

import java.util.List;

public class OptionValueProvider implements ValueProvider {
    @Override
    public boolean supports(MethodParameter parameter, CompletionContext completionContext) {
        return false;
    }

    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext, String[] hints) {
        return null;
    }
}
