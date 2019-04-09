package ru.merann.bopopov.autoshowroom.server.ws;

import ru.merann.bopopov.autoshowroom.server.model.Order;
import ru.merann.bopopov.autoshowroom.server.model.Status;
import ru.merann.bopopov.autoshowroom.server.request.OrderChange;
import ru.merann.bopopov.autoshowroom.server.request.OrderSave;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface OrderWebService {

    // https://www.javaworld.com/article/3215966/web-services-in-java-se-part-2-creating-soap-web-services.html

    Long save(OrderSave order);

    String change(OrderChange order);

    String delete(Long id);

    List<Order> getAll();

    List<Order> getAllByClientIdAndStatus(String username, Status status);

}
