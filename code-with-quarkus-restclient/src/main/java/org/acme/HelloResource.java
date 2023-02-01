package org.acme;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class HelloResource {

    @Inject
    HelloService helloService;

    @GET
    public Uni<String> hello(){
        return helloService.sayHello();
    }

}