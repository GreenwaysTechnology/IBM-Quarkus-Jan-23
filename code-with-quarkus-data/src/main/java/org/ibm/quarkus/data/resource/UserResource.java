package org.ibm.quarkus.data.resource;

import io.smallrye.mutiny.Uni;
import org.ibm.quarkus.data.entity.Customer;
import org.ibm.quarkus.data.entity.User;
import org.ibm.quarkus.data.entity.repositry.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/api/users")
public class UserResource {
    @Inject
    UserRepository userRepository;

    @GET
    public Uni<List<User>> get() {
        return userRepository.listAll();
    }
    //CURD operations
}
