package com.alastifer.shop.controller;

import com.alastifer.shop.dao.ProductsDAO;
import com.alastifer.shop.dao.exception.DAOException;
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

    private final static String PAGE_OK = "/jsp/oneProduct.jsp";

    private final static String ATTRIBUTE_PRODUCT = "product";

    private final static String PARAM_ID = "id";

    private final ProductsDAO dao = new ProductsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer productID = getValue(req.getParameter(PARAM_ID));
        Product product = getProductByID(productID);
        req.setAttribute(ATTRIBUTE_PRODUCT, product);
        req.getRequestDispatcher(PAGE_OK).forward(req, resp);
    }

    private Product getProductByID(final int productID) throws IOException {
        try {
            return dao.getProductByID(productID);
        } catch (NoSuchEntityException | DAOException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    private Integer getValue(String value) throws IOException {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new IOException("Incorrect id \"" + value + "\"", e);
        }
    }

}
