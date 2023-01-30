package org.acme.rest;

import org.acme.service.GreeterService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/greeter")
public class GreeterResource {


    @Inject
    GreeterService greeterServiceHello;
    @Inject
    GreeterService greeterServiceHai;

    @GET
    public String hello() {
        return greeterServiceHello.sayHello();
    }

    @GET
    @Path("/hai")
    public String hai() {
        return greeterServiceHai.sayHai();
    }
}
