package ru.merann.bopopov.autoshowroom.server.rs.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import ru.merann.bopopov.autoshowroom.server.model.Order;
import ru.merann.bopopov.autoshowroom.server.model.OrderRequest;
import ru.merann.bopopov.autoshowroom.server.model.ResultListOrder;
import ru.merann.bopopov.autoshowroom.server.model.Status;
import ru.merann.bopopov.autoshowroom.server.service.OrderService;

import javax.validation.Valid;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-10T11:51:56.409+03:00[Europe/Moscow]")

@Controller
@RequestMapping("${openapi.autoshowroom.base-path:}")
public class ClientsApiController implements ClientsApi {

    private final NativeWebRequest request;
    private final OrderService orderService;

    @org.springframework.beans.factory.annotation.Autowired
    public ClientsApiController(NativeWebRequest request, OrderService orderService) {
        this.request = request;
        this.orderService = orderService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Order> createNewOrder(Long clientId, @Valid OrderRequest orderRequest) {
        Order order = orderService.save(clientId, orderRequest);
        if (order == null) {
            return ResponseEntity
                    .notFound()
                    .header("Error", "Client with client_id not found")
                    .build();
        }
        return ResponseEntity.ok().body(order);
    }

    @Override
    public ResponseEntity<Long> deleteOrder(Long clientId, Long orderId) {
        orderService.delete(orderId);
        return ResponseEntity.ok().body(orderId);
    }

    @Override
    public ResponseEntity<ResultListOrder> getOrdersByClient(Long clientId, @Valid Status status) {
        ResultListOrder listOrder = new ResultListOrder();
        listOrder.setItems((orderService.getOrderByClientAndStatus(clientId, status)));
        return ResponseEntity.ok().body(listOrder);
    }

    @Override
    public ResponseEntity<Order> updateOrder(Long clientId, Long orderId, @Valid OrderRequest orderRequest) {
        Order order = orderService.change(clientId, orderId, orderRequest);
        if (order == null) {
            return ResponseEntity
                    .notFound()
                    .header("Error", "Client or order not found")
                    .build();
        }
        return ResponseEntity.ok().body(order);
    }
}
