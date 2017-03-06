package com.alastifer.shop.controller;

import com.alastifer.shop.dao.ProductsDAO;
import com.alastifer.shop.dao.exception.NoSuchEntityException;
import com.alastifer.shop.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/productDelete.do")
public class ProductDeleteFromBasketController extends HttpServlet {

    private static final String ATTRIBUTE_PRODUCT_BASKET = "productsBasket";

    private static final String PARAM_ID = "id";

    private static final String PARAM_REDIRECT_PAGE_ID = "currentProductID";

    private final ProductsDAO productsDAO = new ProductsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idProduct = req.getParameter(PARAM_ID);

        if (idProduct != null) {
            try {
                HttpSession session = req.getSession();
                Product product = productsDAO.getProductByID(Integer.parseInt(idProduct));
                Map<Product, Integer> oldBasket = (Map<Product, Integer>) session.getAttribute(ATTRIBUTE_PRODUCT_BASKET);
                Integer value = oldBasket.get(product);

                if (value != null) {
                    Map<Product, Integer> newBasket = new LinkedHashMap<>(oldBasket);

                    if (value == 1) {
                        newBasket.remove(product);
                    } else {
                        newBasket.replace(product, value - 1);
                    }

                    session.setAttribute(ATTRIBUTE_PRODUCT_BASKET, Collections.unmodifiableMap(newBasket));
                }

                resp.sendRedirect("product.do?id=" + req.getParameter(PARAM_REDIRECT_PAGE_ID));

            } catch (NumberFormatException | ClassCastException | NoSuchEntityException e) {
                e.printStackTrace();
            }
        }
    }

}
