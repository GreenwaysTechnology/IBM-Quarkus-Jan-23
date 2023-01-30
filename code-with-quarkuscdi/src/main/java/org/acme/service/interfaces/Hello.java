package org.acme.service.interfaces;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named("hello")
public class Hello implements Greeter{
    @Override
    public String sayGreet() {
        return "Hello";
    }
}
