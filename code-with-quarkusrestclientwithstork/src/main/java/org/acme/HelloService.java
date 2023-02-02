package org.acme;

import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {

    public Uni<String> sayHello(){
        return Uni.createFrom().item("Hello Strok");
    }

}