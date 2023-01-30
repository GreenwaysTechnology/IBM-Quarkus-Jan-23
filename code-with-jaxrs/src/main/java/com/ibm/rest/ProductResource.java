package com.ibm.rest;

import com.ibm.rest.enity.Product;
import org.jboss.resteasy.reactive.ResponseHeader;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/products")
public class ProductResource {

    //    @GET
//    public List<Product> listProducts(){
//        Product product = new Product(1,"phone","100",34.90);
//        return List.of(product);
//    }
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response listProducts(){
//        return Response
//                .ok()
//                .header("myheader","MyValue")
//                .entity(List.of(new  Product(1,"phone","100",34.90)))
//                .build();
//    }
    //Reactive Implementation of Response
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public RestResponse<Object> listProducts() {
//        Product product = new Product(1,"phone","100",34.90);
//        return ResponseBuilder.ok().header("myheader","Myvalue").entity(List.of(product)).build();
//    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseHeader(name = "myHeader", value = "Myvalue ")
    public RestResponse<List<Product>> listProducts() {
        System.out.println("Products endpoints Thread " + Thread.currentThread().getName());
        Product product = new Product(1, "phone", "100", 34.90);
        return ResponseBuilder.ok(List.of(product)).build();
    }

    @GET
    @Path("/show")
    //No Content is returned -204- HTTP
    public void showProduct() {
        System.out.println("show Product");
    }
}
