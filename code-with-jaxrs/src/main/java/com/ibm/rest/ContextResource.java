package com.ibm.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/api/containerinfo")
public class ContextResource {

    @GET()
    @Path("/urls")
    public String getUriInfo(@Context UriInfo uriInfo) {
        System.out.println(uriInfo.getAbsolutePath() + " " + uriInfo.getRequestUri());
        return uriInfo.getAbsolutePath() + " " +uriInfo.getRequestUri();
    }

    @GET()
    @Path("/headers")
    public String getHeaders(@Context HttpHeaders headers) {
        System.out.println(headers.getRequestHeaders());
        return headers.getRequestHeaders().toString();
    }
}