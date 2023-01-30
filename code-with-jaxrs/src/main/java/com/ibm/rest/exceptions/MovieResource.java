package com.ibm.rest.exceptions;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api/movies")
public class MovieResource {

    @GET
    @Path("/hero/{name}")
    public Response getHero(@PathParam("name") String name) {
        if (name.equals("myhero")) {
            return Response.ok().entity("Hero " + name).build();
        }
        throw new WebApplicationException();
    }
}
