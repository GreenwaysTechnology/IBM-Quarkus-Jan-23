package org.acme.restcommunicator;


import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//@RegisterRestClient

@RegisterRestClient(configKey = "hello-api")
@Path("/hello")
public interface HelloRestClientService {
    //methods
    @GET
    Uni<String> sayHello();
}
