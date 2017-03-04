package com.alastifer.shop.controller;

import com.alastifer.shop.dao.ProductsDAO;
import com.alastifer.shop.dao.exception.NoSuchEntityException;
import com.alastifer.shop.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product.do")
public class ProductChoiceController extends HttpServlet {

    private final ProductsDAO dao = new ProductsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer productID = Integer.valueOf(req.getParameter("id"));
        Product product = getProductByID(productID);

        if (product == null) {
            req.setAttribute("error", "Sorry! No such product!");
            req.getRequestDispatcher("/jsp/error/product.jsp").forward(req, resp);
        } else {
            req.setAttribute("product", product);
            req.getRequestDispatcher("/jsp/product.jsp").forward(req, resp);
        }
    }

    private Product getProductByID(final int productID) {
        try {
            return dao.getProductByID(productID);
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
        }

        return null;
    }

}
