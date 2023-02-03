package org.acme.microprofile.faulttolerance.entity;


public class Product {
    public Integer id;
    public String name;
    public String countryOfOrigin;
    public Integer price;

    public Product(Integer id, String name, String countryOfOrigin, Integer price) {
        this.id = id;
        this.name = name;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
    }

    public Product() {
    }
}
