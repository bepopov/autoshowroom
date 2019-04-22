package ru.merann.bopopov.autoshowroom.restclient.service;

import org.springframework.shell.CompletionProposal;

import java.util.List;

public interface ValueProviderService {

    List<CompletionProposal> getMakes(String text);

    List<CompletionProposal> getModels(String make, String text, String parameterValue);

    List<CompletionProposal> getOptions(String text, String parameterValue);

    List<CompletionProposal> getStatuses();

    List<CompletionProposal> getOrdersByClient();

}
