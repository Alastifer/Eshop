package com.alastifer.shop.dao;

import com.alastifer.shop.dao.exception.DAOException;
import com.alastifer.shop.dao.exception.NoSuchEntityException;
import com.alastifer.shop.entity.Product;

import java.util.List;

public interface DAO {

    Product getProductByID(final Integer id) throws NoSuchEntityException, DAOException;

    List<Product> getAllProducts() throws DAOException;

}
