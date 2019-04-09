package ru.merann.bopopov.autoshowroom.server.rs.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import ru.merann.bopopov.autoshowroom.server.rs.model.Order;
import ru.merann.bopopov.autoshowroom.server.rs.model.OrderRequest;
import ru.merann.bopopov.autoshowroom.server.rs.service.RestOrderService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-09T15:03:19.360+03:00[Europe/Moscow]")

@Controller
@RequestMapping("${openapi.autoshowroom.base-path:}")
public class OrdersApiController implements OrdersApi {

    private final NativeWebRequest request;
    private RestOrderService orderService;

    @org.springframework.beans.factory.annotation.Autowired
    public OrdersApiController(NativeWebRequest request, RestOrderService orderService) {
        this.request = request;
        this.orderService = orderService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> changeOrder(Integer id, @Valid OrderRequest orderRequest) {
        orderService.change(id, orderRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> createNewOrder(@Valid OrderRequest orderRequest) {
        orderService.save(orderRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteOrder(Integer id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Order> getOrderById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Order>> getOrdersByStatusAndUsername(@Valid String status, @Valid String username) {
        HttpHeaders headers = new HttpHeaders();
        if (status != null && username != null) {
            List<Order> orders = orderService.getOrderByClientAndStatus(status, username);
            return ResponseEntity.ok().headers(headers).body(orders);
        }
        else if (status == null && username == null) {
            List<Order> orders = orderService.getOrders();
            return ResponseEntity.ok().headers(headers).body(orders);
        }
        return ResponseEntity.notFound().build();
    }
}
