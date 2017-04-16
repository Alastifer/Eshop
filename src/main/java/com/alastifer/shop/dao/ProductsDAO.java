package com.alastifer.shop.dao;

import com.alastifer.shop.dao.exception.DAOException;
import com.alastifer.shop.dao.exception.NoSuchEntityException;
import com.alastifer.shop.entity.Product;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class ProductsDAO {

    private static final String QUERY_SELECT_ALL = "SELECT id, name FROM products";

    private static final String QUERY_SELECT_BY_ID = "SELECT id, name FROM products WHERE id=?";

    public Product getProductByID(final Integer id) throws NoSuchEntityException, DAOException {
        DataSource dataSource = initDataSource();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(QUERY_SELECT_BY_ID)){

            pStatement.setInt(1, id);
            ResultSet resultSet = pStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString(2);
                return new Product(name, id);
            } else {
                throw new NoSuchEntityException("No product with id " + id);
            }

        } catch (SQLException e) {
            throw new DAOException("Error in database " + e.getMessage(), e);
        }
    }

    public List<Product> getAllProducts() throws DAOException {
        List<Product> products = new ArrayList<>();

        DataSource dataSource = initDataSource();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                products.add(new Product(name, id));
            }

        } catch (SQLException e) {
            throw new DAOException("Error in database " + e.getMessage(), e);
        }

        return products;
    }

    private DataSource initDataSource() throws DAOException {
        try {
            Context context = new InitialContext();
            return  (DataSource) context.lookup("java:comp/env/jdbc/eShop");
        } catch (NamingException e) {
            throw new DAOException("Error in initialContext: " + e.getMessage(), e);
        }
    }

}
