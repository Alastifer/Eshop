package com.alastifer.shop.dao;

import com.alastifer.shop.dao.exception.DAOException;
import com.alastifer.shop.dao.exception.NoSuchEntityException;
import com.alastifer.shop.entity.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class ProductsDAO implements DAO {

    private static final String HQL_QUERY_SELECT_ALL = "FROM Product";

    private static SessionFactory sessionHibernate = new Configuration().configure().buildSessionFactory();

    public Product getProductByID(final Integer id) throws NoSuchEntityException, DAOException {
        try (Session session = sessionHibernate.openSession()) {
            Product product = session.get(Product.class, id);

            if (product == null) {
                throw new NoSuchEntityException("No product with id = " + id);
            }

            return product;
        } catch (HibernateException e) {
            throw new DAOException("Problem with database", e);
        }
    }

    public List<Product> getAllProducts() throws DAOException {
        try (Session session = sessionHibernate.openSession()) {
            return session.createQuery(HQL_QUERY_SELECT_ALL).list();
        } catch (HibernateException e) {
            throw new DAOException("Problem with database", e);
        }
    }

}
