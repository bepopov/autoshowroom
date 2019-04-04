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
        String parameterName = parameter.getParameterName();
        int size = words.size();
        if (size > 1) {
            String previousWord = words.get(size - 2);
            String currentWord = words.get(size - 1);
            return previousWord.equals("--options") || currentWord.contains(" & ");
        }
        return false;
    }

    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext, String[] hints) {
        String text = completionContext.currentWordUpToCursor();
        int index = text.indexOf("&");
        if (index != -1 && index < text.length() - 2) {
            text = text.substring(index + 2);
        }
        else if (index == text.length() - 2) {
            text = null;
        }
        return providerService.getOptions(text);
    }
}
