package ru.merann.bopopov.autoshowroom.groovy_server.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.merann.bopopov.autoshowroom.groovy_server.model.Order

@Repository
interface OrderRepository extends CrudRepository<Order, Long> {
}
