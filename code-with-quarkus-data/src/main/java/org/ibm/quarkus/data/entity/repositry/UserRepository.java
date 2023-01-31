package org.ibm.quarkus.data.entity.repositry;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import org.ibm.quarkus.data.entity.User;

import javax.enterprise.context.ApplicationScoped;

//Panache with DAO/Repository pattern
@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    //put custom query
}
