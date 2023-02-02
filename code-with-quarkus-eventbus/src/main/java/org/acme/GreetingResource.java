package org.acme;


import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {


    @Inject
    EventBus eventBus;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {
        //Request-Reply pattern - with ack
        return eventBus.<String>request(GreetingService.class.getName().toString(), "")
                .onItem().transform(Message::body);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public String greet(@PathParam("name") String name) {
        //Request-Reply (request and forget) pattern : point to point
        EventBus bus = eventBus.requestAndForget("greeting", name);
        return "Processed";
    }

    //pubsub pattern publishes messages to many subscribers

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/push/{msg}")
    public String notifyPush(@PathParam("msg") String msg) {
        //pub - sub pattern
        eventBus.publish("notification", msg).toString();
        return "Published";
    }


}