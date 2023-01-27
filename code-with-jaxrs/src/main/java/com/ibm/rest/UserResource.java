package com.ibm.rest;

import javax.ws.rs.*;

/**
 * Resource class must be named as DomainNameResource
 */

@Path("/api/users")
public class UserResource {

    @GET
    public String getUsers(){
        return "Users";
    }

    @GET
    @Path("/{id}")
    public String getUserById(@PathParam("id") Integer id) {
        return "User By Id" + id;
    }
    //Read Users with state and city
    //http://localhost:8080/api/users/address;city=Coimbatore;state=Tamil nadu
    @GET
    @Path("/address")
    public String getUserCityAndState(@MatrixParam("city") String city, @MatrixParam("state") String state) {
        return city + state  ;
    }

    //http://localhost:8080/api/users/mailphone - default values for Query Parameter
    //http://localhost:8080/api/users/mailphone?mailId=test@gmail.com&mobileNo=0000000
    @GET
    @Path("/mailphone")
    public String getUsersMailAndPhone(@QueryParam("mailId") @DefaultValue("contact@gmail.com") String mailId , @QueryParam("mobileNo") @DefaultValue("1234567890") String mobileNo) {
        return mailId + mobileNo;
    }
    @POST
    public String save(){
        return "Users save";
    }
    @PUT
    public String update(){
        return "Users update";
    }
    @DELETE
    public String remove(){
        return "Users remove";
    }
}
