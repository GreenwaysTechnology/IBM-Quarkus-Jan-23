package org.acme;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "greeting.name", defaultValue = "Default Name")
    String name;
    @ConfigProperty(name = "greeting.message", defaultValue = "Default message")
    String message;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return name + " " +  message;
    }
}