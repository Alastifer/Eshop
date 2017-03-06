package com.alastifer.shop.controller;

import com.alastifer.shop.dao.ProductsDAO;
import com.alastifer.shop.dao.exception.NoSuchEntityException;
import com.alastifer.shop.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/productAdd.do")
public class ProductAddToBasketController extends HttpServlet {

    private static final String ATTRIBUTE_PRODUCT_BASKET = "productsBasket";

    private static final String PARAM_ID = "id";

    private final ProductsDAO productsDAO = new ProductsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idProduct = req.getParameter(PARAM_ID);

        if (idProduct != null) {
            try {
                Product product = productsDAO.getProductByID(Integer.valueOf(idProduct));
                HttpSession session = req.getSession();

                Map<Product, Integer> oldBasket = (Map<Product, Integer>) session.getAttribute(ATTRIBUTE_PRODUCT_BASKET);

                if (oldBasket == null) {
                    session.setAttribute(ATTRIBUTE_PRODUCT_BASKET, Collections.singletonMap(product, 1));
                } else {
                    Map<Product, Integer> newBasket = new LinkedHashMap<>(oldBasket);
                    if (!newBasket.containsKey(product)) {
                        newBasket.put(product, 1);
                    } else {
                        newBasket.replace(product, newBasket.get(product) + 1);
                    }

                    session.setAttribute(ATTRIBUTE_PRODUCT_BASKET, Collections.unmodifiableMap(newBasket));
                }

                resp.sendRedirect("product.do?id=" + idProduct);
            } catch (NumberFormatException | ClassCastException | NoSuchEntityException e) {
                e.printStackTrace();
            }
        }

    }

}
