package ru.merann.bopopov.autoshowroom.groovy_server.model

import org.springframework.data.cassandra.core.mapping.UserDefinedType

@UserDefinedType("car")
class Car {

    String model

    String make

    List<String> options

}
