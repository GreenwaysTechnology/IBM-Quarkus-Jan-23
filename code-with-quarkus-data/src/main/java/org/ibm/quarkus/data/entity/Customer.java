package org.ibm.quarkus.data.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;

import javax.persistence.Column;
import javax.persistence.Entity;

//Active Record Pattern helps to keep biz logic(Service layer) and Entity  in the same file
@Entity
public class Customer extends PanacheEntity {
    @Column
    public String name;
    @Column
    public String city;

    public Customer() {
    }
    //findByName:Custom methods
    public static Uni<Customer> findByName(String name){
        return find("name",name).firstResult();
    }

    public String getName() {
        return name.toUpperCase();
    }

    public Customer(String name, String city) {
        this.name = name;
        this.city = city;
    }
}