package ru.merann.bopopov.autoshowroom.server.ws;

import ru.merann.bopopov.autoshowroom.server.model.Order;
import ru.merann.bopopov.autoshowroom.server.model.OrderRequest;
import ru.merann.bopopov.autoshowroom.server.model.Status;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface OrderWebService {

    // https://www.javaworld.com/article/3215966/web-services-in-java-se-part-2-creating-soap-web-services.html

    Order save(Long clientId, OrderRequest order);

    Order change(Long clientId, Long orderId, OrderRequest order);

    Long delete(Long id);

    List<Order> getAll();

    List<Order> getAllByClientIdAndStatus(Long clientId, Status status);

}
