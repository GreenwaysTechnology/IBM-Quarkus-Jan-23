package com.ibm.rest.enity;

public class Product {
    private  int productId;
    private String name;
    private String qty;
    private double price;

    public Product(int productId, String name, String qty, double price) {
        this.productId = productId;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
