package org.ibm.quarkus.data.resource;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.CompositeException;
import io.smallrye.mutiny.Uni;
import org.ibm.quarkus.data.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Path("/api/customers")
public class CustomerResource {

    @GET
    public Uni<List<Customer>> get() {
        return Customer.listAll();
    }

    @GET
    @Path("{name}")
    public Uni<Customer> findByName(@PathParam("name") String name) {
        return Customer.findByName(name);
    }

    //Get Customer byId
    @GET
    @Path("/byid/{id}")
    public Uni<Customer> findById(Long id) {
        return Customer.findById(id);
    }

    //create /save : Transaction must be enabled when we do add or delete,update
    //Incase of reactive transaction is enabled via code, in non reactive env
    //we use @Transactional annoation
    @POST
    public Uni<Response> create(Customer customer) {
        //handle error
        if (customer == null || customer.name == null) {
            //throw exception
            throw new WebApplicationException("Customer Not found", 422);
        }
        return Panache
                .withTransaction(customer::persist)
                .replaceWith(Response.ok(customer)
                        .status(Response.Status.CREATED)
                        .build());
    }
    //delete a row

    @DELETE
    @Path("/byid/{id}")
    public Uni<Response> delete(Long id) {
        return Panache
                .withTransaction(() -> Customer.deleteById(id))
                .map(deleted -> deleted
                        ? Response.ok().status(Response.Status.NO_CONTENT).build()
                        : Response.ok().status(Response.Status.NOT_FOUND).build());
    }

    //Update

    @PUT
    @Path("/byid/{id}")
    public Uni<Response> update(Long id, Customer customer) {
        if (customer == null || customer.name == null) {
            throw new WebApplicationException("Customer Not Found", 422);
        }
        //Update
        return Panache.withTransaction(() -> Customer.<Customer>findById(id)
                .onItem()
                .ifNotNull()
                .invoke(entity -> entity.city = customer.city) //update logic
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.ok().status(Response.Status.NOT_FOUND)::build));

    }

    //Error handler
    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            System.out.println("Failed to handle Exception");
            Throwable throwable = exception;
            int code = 500;
            if (throwable instanceof WebApplicationException) {
                //extract the exception code which is thrown by application code
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }
            //How to handle database exception: like if you insert row which is already there
            //duplicate row.
            if (throwable instanceof CompositeException) {
                throwable = ((CompositeException) throwable).getCause();
            }
            //
            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", throwable.getClass().getName());
            exceptionJson.put("code", code);
            if (exception.getMessage() != null) {
                exceptionJson.put("error", throwable.getMessage());
            }
            return Response.status(code).entity(exception).build();

        }
    }

}
