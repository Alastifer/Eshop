package com.alastifer.shop.controller;

import com.alastifer.shop.dao.ProductsDAO;
import com.alastifer.shop.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class ProductAllViewController extends HttpServlet {

    private final ProductsDAO dao = new ProductsDAO();

    private final static String ATTRIBUTE_PRODUCTS = "products";

    private final static String PAGE_ALL_PRODUCTS = "/jsp/products.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = dao.getAllProducts();
        req.setAttribute(ATTRIBUTE_PRODUCTS, products);
        req.getRequestDispatcher(PAGE_ALL_PRODUCTS).forward(req, resp);
    }

}
