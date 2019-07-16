package ru.merann.bopopov.autoshowroom.groovy_server.service

import ru.merann.bopopov.autoshowroom.groovy_server.model.OrderRequest

interface OrderService {

    void save(OrderRequest order)

}