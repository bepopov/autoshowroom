package ru.merann.bopopov.autoshowroom.restclient.config;

import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;
import ru.merann.bopopov.autoshowroom.restclient.service.ConnectionService;

@Component
public class ConnectedPromptProvider implements PromptProvider {

    private ConnectionService connectionService;

    public ConnectedPromptProvider(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Override
    public AttributedString getPrompt() {
        String msg = String.format("SOAP Client (%s)>", this.connectionService.isConnected() ? connectionService.getUsername() : "Disconnected");
        return new AttributedString(msg);
    }
}
