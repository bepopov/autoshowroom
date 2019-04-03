package ru.merann.bopopov.autoshowroom.server.ws;

import ru.merann.bopopov.autoshowroom.server.model.Model;
import ru.merann.bopopov.autoshowroom.server.model.Option;
import ru.merann.bopopov.autoshowroom.server.model.Status;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface ValueProviderWebService {

    List<String> getMakes(String text);

    List<Model> getModels(String make, String text);

    List<Option> getOptions(String text);

    List<Status> getStatuses();
}
