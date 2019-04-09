package ru.merann.bopopov.autoshowroom.server.rs.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import ru.merann.bopopov.autoshowroom.server.rs.model.Order;
import ru.merann.bopopov.autoshowroom.server.rs.model.OrderRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-09T12:54:49.820+03:00[Europe/Moscow]")

@Controller
@RequestMapping("${openapi.autoshowroom.base-path:}")
public class OrdersApiController implements OrdersApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public OrdersApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> changeOrder(Integer id, @Valid OrderRequest orderRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> createNewOrder(@Valid OrderRequest orderRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteOrder(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Order> getOrderById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Order>> getOrdersByStatusAndUsername(@Valid String status, @Valid String username) {
        return null;
    }
}
