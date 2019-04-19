package ru.merann.bopopov.autoshowroom.restclient;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

@EnableAutoConfiguration
@ComponentScan("ru.merann.bopopov.autoshowroom.restclient")
public class RestWebClient extends SpringBootServletInitializer {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        return new ServletRegistrationBean(servlet, "*.jsf");
    }

    @Bean
    public FilterRegistrationBean rewriteFilter() {
        FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
        rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST,
                DispatcherType.ASYNC, DispatcherType.ERROR));
        rwFilter.addUrlPatterns("/*");
        return rwFilter;
    }


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RestWebClient.class);

        app.run(args);
        /*
        WebClientService clientService = new WebClientServiceImpl();
        OrderRequest orderRequest = new OrderRequest();
        OrderRequestCar car = new OrderRequestCar();
        car.setMake(1L);
        car.setModel(1L);
        orderRequest.setCar(car);
        List<Long> longs = new ArrayList<>();
        longs.add(1L);
        orderRequest.setOptions(longs);
        ResultListOrder listOrder = clientService.getOrdersByStatus(Status.ACCEPTED, 1L);
        System.out.println(listOrder.toString());
        */
    }

}
