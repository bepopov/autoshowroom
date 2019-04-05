package ru.merann.bopopov.autoshowroom.soapclient.valueproviders;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.soapclient.service.ValueProviderService;

import java.util.List;

@Component
public class OptionValueProvider implements ValueProvider {

    private ValueProviderService providerService;

    public OptionValueProvider(ValueProviderService providerService) {
        this.providerService = providerService;
    }

    @Override
    public boolean supports(MethodParameter parameter, CompletionContext completionContext) {
        List<String> words = completionContext.getWords();
        int size = words.size();
        if (size > 1) {
            String previousWord = words.get(size - 2);
            return previousWord.equals("--options");
        }
        return false;
    }

    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext, String[] hints) {
        String text = completionContext.currentWordUpToCursor();
        return providerService.getOptions(text, null);
    }
}
