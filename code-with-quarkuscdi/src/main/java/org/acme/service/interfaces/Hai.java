package org.acme.service.interfaces;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named("hai")
public class Hai implements Greeter {
    @Override
    public String sayGreet() {
        return "Hai";
    }
}