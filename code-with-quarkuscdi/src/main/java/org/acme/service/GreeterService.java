package org.acme.service;


import org.acme.service.interfaces.Greeter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class GreeterService {
    @Inject
    @Named("hello")
    Greeter greeterHello;

    @Inject
    @Named("hai")
    Greeter getGreeterHai;

    @PostConstruct
    public void init() {
        System.out.println("Init is called");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroy is called");
    }

    public String sayHello() {
        return greeterHello.sayGreet();
    }

    public String sayHai() {
        return getGreeterHai.sayGreet();
    }
}
