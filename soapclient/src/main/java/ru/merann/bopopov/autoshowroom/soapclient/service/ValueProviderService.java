package ru.merann.bopopov.autoshowroom.soapclient.service;

import org.springframework.shell.CompletionProposal;
import ru.merann.bopopov.autoshowroom.server.ws.Model;
import ru.merann.bopopov.autoshowroom.server.ws.Option;
import ru.merann.bopopov.autoshowroom.server.ws.Order;

import java.util.List;

public interface ValueProviderService {

    List<CompletionProposal> getMakes(String text);

    List<CompletionProposal> getModels(String make, String text, String parameterValue);

    List<CompletionProposal> getOptions(String text,  String parameterValue);

    List<CompletionProposal> getStatuses();

    List<CompletionProposal> getOrdersByClient();

}
