package ru.merann.bopopov.autoshowroom.server;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.merann.bopopov.autoshowroom.server.ws.ConnectionWebService;
import ru.merann.bopopov.autoshowroom.server.ws.OrderWebService;
import ru.merann.bopopov.autoshowroom.server.ws.ValueProviderWebService;

import javax.xml.ws.Endpoint;

@SpringBootApplication
@ComponentScan(basePackages = {
        "ru.merann.bopopov.autoshowroom.server",
        "org.openapitools.configuration"})
public class ServerApp {

    @Autowired
    private Bus bus;

    @Autowired
    private OrderWebService orderWebService;

    @Autowired
    private ValueProviderWebService valueProviderWebService;

    @Autowired
    private ConnectionWebService connectionWebService;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ServerApp.class);
        app.run(args);
    }

    @Bean
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    public Endpoint orderServiceEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(cxf(), orderWebService);
        endpoint.publish("/orderService");
        return endpoint;
    }

    @Bean
    public Endpoint valueProviderServiceEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(cxf(), valueProviderWebService);
        endpoint.publish("/valueProviderService");
        return endpoint;
    }

    @Bean
    public Endpoint connectionServiceEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(cxf(), connectionWebService);
        endpoint.publish("/connectionService");
        return endpoint;
    }
}
