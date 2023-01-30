package org.acme.rest;

import org.acme.service.BookService;
import org.acme.service.interceptors.LogEvent;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/api/books")
public class BookResource {

    @Inject
    BookService bookService;
    @GET
    @LogEvent
    public Response createBook(){
        return Response.ok().entity(bookService.createBook()).build();
    }

}