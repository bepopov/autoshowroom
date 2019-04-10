package ru.merann.bopopov.autoshowroom.server.ws;

import ru.merann.bopopov.autoshowroom.server.model.Order;
import ru.merann.bopopov.autoshowroom.server.model.OrderRequest;
import ru.merann.bopopov.autoshowroom.server.model.Status;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface OrderWebService {

    // https://www.javaworld.com/article/3215966/web-services-in-java-se-part-2-creating-soap-web-services.html

    Long save(OrderRequest order);

    String change(OrderRequest order);

    String delete(Long id);

    List<Order> getAll();

    List<Order> getAllByClientIdAndStatus(String username, Status status);

}
