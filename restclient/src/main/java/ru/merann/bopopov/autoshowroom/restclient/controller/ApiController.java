package ru.merann.bopopov.autoshowroom.restclient.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component(value = "apiController")
@ELBeanName(value = "apiController")
@Join(path = "/product", to = "/product-form.jsf")
public class ApiController {

    public String save() {
        System.out.println("Hello!");
        return "/product";
    }

}
