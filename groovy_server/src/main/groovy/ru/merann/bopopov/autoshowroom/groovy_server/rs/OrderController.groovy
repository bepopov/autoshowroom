package ru.merann.bopopov.autoshowroom.groovy_server.rs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import ru.merann.bopopov.autoshowroom.groovy_server.model.OrderRequest
import ru.merann.bopopov.autoshowroom.groovy_server.service.OrderService

@RestController
@RequestMapping("/order")
class OrderController {

    @Autowired
    OrderService orderService

    @RequestMapping(value = "",
    produces = "application/json",
    consumes = "application/json",
    method = RequestMethod.POST)
    void saveOrder(@RequestBody OrderRequest orderRequest) {
        orderService.save(orderRequest)
    }

}