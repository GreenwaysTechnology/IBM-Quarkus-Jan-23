package org.acme.rest;

import io.smallrye.mutiny.Uni;
import org.acme.service.CustomerService;
import org.acme.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/customers")
public class CustomerResource {

    @Inject
    CustomerService customerService;
    @Inject
    ProductService productService;

    @GET
    public Uni<String> getCustomer(){
          return customerService.getCustomers();
    }
    @GET
    @Path("/products")
    public Uni<String> getProducts(){
        return productService.getProducts();
    }
}
