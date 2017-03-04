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

@WebServlet("/productAll")
public class ProductChoiceController extends HttpServlet {

    private final static String PAGE_OK = "/jsp/product.jsp";

    private final static String PAGE_ERROR = "/jsp/error/product.jsp";

    private final static String ATTRIBUTE_ERROR = "error";

    private final static String ATTRIBUTE_PRODUCT = "product";

    private final static String PARAM_ID = "id";

    private final ProductsDAO dao = new ProductsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer productID = Integer.valueOf(req.getParameter(PARAM_ID));
        Product product = getProductByID(productID);

        if (product == null) {
            req.setAttribute(ATTRIBUTE_ERROR, "Sorry! No such product!");
            req.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
        } else {
            req.setAttribute(ATTRIBUTE_PRODUCT, product);
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
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
