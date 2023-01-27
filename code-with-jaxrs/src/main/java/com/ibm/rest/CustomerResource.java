package com.ibm.rest;

import com.ibm.rest.enity.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/customers")
public class CustomerResource {

    //return single Customer
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer() {
        Customer customer = new Customer(1, "Subramanian", "Murugan", "Coimbatore");
        return customer;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomerList() {
        Customer customer = new Customer(1, "Subramanian", "Murugan", "Coimbatore");
        return List.of(customer, customer, customer);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Customer save(Customer customer) {
        System.out.println(customer.getFirstName() + customer.getLastName());
        return customer;
    }
}
