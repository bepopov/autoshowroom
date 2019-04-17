package ru.merann.bopopov.autoshowroom.server.rs.api;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import ru.merann.bopopov.autoshowroom.server.model.ResultListOrder;
import ru.merann.bopopov.autoshowroom.server.service.OrderService;

import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-10T11:51:56.409+03:00[Europe/Moscow]")

@Controller
@RequestMapping("${openapi.autoshowroom.base-path:}")
public class OrdersApiController implements OrdersApi {

    private static final Logger logger = LogManager.getLogger(OrdersApiController.class);

    private final NativeWebRequest request;
    private final OrderService orderService;

    @org.springframework.beans.factory.annotation.Autowired
    public OrdersApiController(NativeWebRequest request, OrderService orderService) {
        this.request = request;
        this.orderService = orderService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<ResultListOrder> getAllOrders() {
        ResultListOrder listOrder = new ResultListOrder();
        listOrder.setItems(orderService.getOrders());
        logger.log(Level.INFO, String.format(
                "Order list successfully added to response body. Returning 200 OK to client"));
        return ResponseEntity.ok().body(listOrder);
    }
}
