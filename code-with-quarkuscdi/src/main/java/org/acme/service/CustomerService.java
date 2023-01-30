package org.acme.service;

import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerService {

    public Uni<String> getCustomers() {
        return Uni.createFrom().item("Customer Service");
    }
}
