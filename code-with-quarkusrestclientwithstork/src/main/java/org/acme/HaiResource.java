package org.acme;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hai")
public class HaiResource {

    @Inject
    HaiService haiService;

    @GET
    public String sayHai() {
        return haiService.sayHai();
    }
}
