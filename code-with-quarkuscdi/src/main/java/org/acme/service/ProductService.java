package org.acme.service;

import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductService {

    public Uni<String> getProducts() {
        return Uni.createFrom().item("Product Service");
    }
}
