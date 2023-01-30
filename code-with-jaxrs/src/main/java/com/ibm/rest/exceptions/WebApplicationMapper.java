package com.ibm.rest.exceptions;


import javax.ws.rs.ext.Provider;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Provider
public class WebApplicationMapper  implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException exception) {
        return Response.ok().entity("Hero Not Found").build();
    }
}
