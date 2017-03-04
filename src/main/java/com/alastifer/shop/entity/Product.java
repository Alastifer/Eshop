package com.alastifer.shop.entity;

public class Product {

    private final String name;

    private final int id;

    public Product(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
