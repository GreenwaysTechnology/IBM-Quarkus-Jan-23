package org.acme;

import org.acme.service.GreetingService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    //private GreetingService greetingService = new GreetingService();

    //DI - Field injection
    @Inject
    // private GreetingService greetingServiceOne;
            GreetingService greetingServiceOne;

    //    private GreetingService greetingServiceTwo;
    GreetingService greetingServiceTwo;


    //    private GreetingService greetingServiceThree;
    GreetingService greetingServiceThree;


    @Inject
    public void setGreetingServiceTwo(GreetingService greetingServiceTwo) {
        this.greetingServiceTwo = greetingServiceTwo;
    }

    @Inject
    public GreetingResource(GreetingService greetingServiceThree) {
        this.greetingServiceThree = greetingServiceThree;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        // return greetingServiceOne.sayHello();
        //  return greetingServiceTwo.sayHello();
        return greetingServiceThree.sayHello();
    }
}