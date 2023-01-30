package org.acme.service;


import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped // ioc annotation, container creates object for us and make it ready to use
public class GreetingService {
    //biz method
    public String sayHello(){
        return "Hello from RESTEasy Reactive";
    }

}
