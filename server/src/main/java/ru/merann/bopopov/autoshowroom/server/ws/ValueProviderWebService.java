package ru.merann.bopopov.autoshowroom.server.ws;

import ru.merann.bopopov.autoshowroom.server.model.*;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface ValueProviderWebService {

    List<Make> getMakes(String text);

    List<Model> getModels(Long makeId, String text);

    List<Option> getOptions(String text);

    List<Status.NameEnum> getStatuses();

    List<Order> getOrdersByClient(Long id);
}
