package ru.merann.bopopov.autoshowroom.groovy_server.rs

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import ru.merann.bopopov.autoshowroom.groovy_server.model.OrderRequest
import ru.merann.bopopov.autoshowroom.groovy_server.service.OrderService
import ru.merann.bopopov.autoshowroom.grpc.CarRequest
import ru.merann.bopopov.autoshowroom.grpc.OrderRequestOuterClass
import ru.merann.bopopov.autoshowroom.grpc.OrderServiceGrpc

@RestController
@RequestMapping("/clients")
class OrderController {

    @Autowired
    OrderService orderService
    @Autowired
    OrderServiceGrpc.OrderServiceBlockingStub serviceGrpc
    private static final Logger logger = LogManager.getLogger(OrderController.class);

    @RequestMapping(value = "/{userId}/orders",
    produces = "application/json",
    consumes = "application/json",
    method = RequestMethod.POST)
    ResponseEntity<String> saveOrder(@RequestBody OrderRequest orderRequest, @PathVariable("userId") Long userId) {
        orderService.save(orderRequest, userId)
        CarRequest request = CarRequest.newBuilder()
                .setMake(orderRequest.getCar().getMake())
                .setModel(orderRequest.getCar().getModel())
                .build()
        OrderRequestOuterClass.OrderRequest.Builder builder =
                OrderRequestOuterClass.OrderRequest.newBuilder()
                        .setClient(userId)
                        .setCar(request)
        if (orderRequest.getOptions() != null) {
            builder.addAllOption(orderRequest.getOptions())
        }
        else {
            builder.addOption(0)
        }
        OrderRequestOuterClass.OrderResponse response = serviceGrpc.save(builder.build())
        if (response.getStatus().equals(OrderRequestOuterClass.OrderResponse.SaveStatus.FAIL)) {
            logger.log(Level.INFO, String.format("Received gRPC response: %s", OrderRequestOuterClass.OrderResponse.SaveStatus.FAIL.toString()));
            throw new Exception("Some error on java client")
        }
        logger.log(Level.INFO, String.format("Received gRPC response: %s", OrderRequestOuterClass.OrderResponse.SaveStatus.SUCCESS.toString()));
        ResponseEntity.ok().body(response.toString())
    }

}