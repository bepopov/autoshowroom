package ru.merann.bopopov.autoshowroom.groovy_server.repository

import org.springframework.data.repository.CrudRepository

interface OrderRepository extends CrudRepository<String, Long> {
}
