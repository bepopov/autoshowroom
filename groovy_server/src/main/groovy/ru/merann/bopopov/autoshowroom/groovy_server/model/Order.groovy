package ru.merann.bopopov.autoshowroom.groovy_server.model

import org.springframework.data.cassandra.core.mapping.Table

@Table("orders")
class Order {

    Long id

    Car car

    Client client

    Status status

}
