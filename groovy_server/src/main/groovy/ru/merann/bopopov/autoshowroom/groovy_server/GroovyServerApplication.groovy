package ru.merann.bopopov.autoshowroom.groovy_server

import io.grpc.ManagedChannelBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import ru.merann.bopopov.autoshowroom.grpc.OrderServiceGrpc

@SpringBootApplication
class GroovyServerApplication {

    static void main(String [] args) {
        SpringApplication.run(GroovyServerApplication.class, args)
    }

    @Value('${java-server.host}')
    String host
    @Value('${java-server.port}')
    Integer port

    @Bean
    OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub() {
        return OrderServiceGrpc.newBlockingStub(ManagedChannelBuilder
                .forAddress(host, port).usePlaintext().build())
    }

}
