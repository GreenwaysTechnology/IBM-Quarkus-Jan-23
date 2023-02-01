package org.acme;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "greeting.message", defaultValue = "Greeting")
    String message;
    @ConfigProperty(name = "greeting.name", defaultValue = "Quarkus")
    String name;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message + " " + name;
    }

    @GET
    @Path("/configviacode")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloconfig() {
        String name = ConfigProvider.getConfig().getOptionalValue("greeting.name", String.class).orElse("Quarkus");
        String message = ConfigProvider.getConfig().getOptionalValue("greeting.message", String.class).orElse("Greeting");
        return name + message;
    }

}