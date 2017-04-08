package com.alastifer.shop.dao;

import com.alastifer.shop.dao.exception.NoSuchEntityException;
import com.alastifer.shop.entity.Product;

import java.util.*;

public class ProductsDAO {

    private final List<Product> database = new ArrayList<>();

    public ProductsDAO() {
        database.add(new Product("Sugar", 1));
        database.add(new Product("Bread", 2));
        database.add(new Product("Paper", 3));
        database.add(new Product("Pen", 4));
    }

    public Product getProductByID(final Integer id) throws NoSuchEntityException {
        if (id > database.size() || id <= 0) {
            throw new NoSuchEntityException("No element with ID " + id + " in database");
        }

        Product product = database.get(id - 1);

        if (product == null) {
            throw new NoSuchEntityException("No element with ID " + id + " in database");
        }

        return product;
    }

    public Product getProductByName(final String productName) throws NoSuchEntityException {
        for (Product product : database) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }

        throw new NoSuchEntityException("No element with name \"" + productName + "\" in database");
    }

    public List<Product> getAllProducts() {
        return database;
    }

}
