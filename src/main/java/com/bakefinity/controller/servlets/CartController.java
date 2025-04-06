package com.bakefinity.controller.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.bakefinity.controller.services.impls.ProductServiceImpl;
import com.bakefinity.controller.services.interfaces.ProductService;
import com.bakefinity.model.dtos.CartDTO;
import com.bakefinity.model.dtos.ProductDTO;
import com.bakefinity.model.dtos.UserDTO;

import com.bakefinity.utils.CartPrice;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(value = {"/cart", "/order-summary"})
public class CartController extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = ProductServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(true);

            @SuppressWarnings("unchecked")
            Map<Integer, CartDTO> cart = (Map<Integer, CartDTO>) session.getAttribute("cart");
            if (cart == null) {
                cart = new HashMap<>();
                session.setAttribute("cart", cart);
            }

            Map<Integer, ProductDTO> products = new HashMap<>();
            for (CartDTO cartItem : cart.values()) {
                ProductDTO product = productService.getProductById(cartItem.getProductId());
                if (product != null) {
                    products.put(cartItem.getProductId(), product);
                }
            }

            req.setAttribute("products", products);
            req.setAttribute("totalPrice", CartPrice.calculateTotalPrice(cart));

            String path = "/order-summary".equals(req.getServletPath())
                    ? "/checkout"
                    : "/views/user/cart.jsp";

            req.getRequestDispatcher(path).forward(req, resp);

        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        @SuppressWarnings("unchecked")
        Map<Integer, CartDTO> cart = (Map<Integer, CartDTO>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }

        UserDTO user = (UserDTO) session.getAttribute("user");
        boolean isGuest = (user == null);

        String productID = req.getParameter("productID");
        String productQuantity = req.getParameter("quantity");

        if (productID == null || productQuantity == null) {
            System.out.println("product"+productID);

            sendJsonResponse(resp, "error", "Missing parameters", cart.size(), CartPrice.calculateTotalPrice(cart));
            return;
        }

        try {
            int productId = Integer.parseInt(productID);
            int productQ = Integer.parseInt(productQuantity);

            ProductDTO product = productService.getProductById(productId);
            if (product == null) {
                sendJsonResponse(resp, "error", "Product not found", cart.size(), CartPrice.calculateTotalPrice(cart));
                return;
            }

            if (productQ == 0) {
                cart.remove(productId);
                sendJsonResponse(resp, "success", "Item removed from cart", cart.size(), CartPrice.calculateTotalPrice(cart));
                return;
            }

            CartDTO cartItem = new CartDTO((isGuest ? null : user.getId()), productId, productQ);
            cart.put(productId, cartItem);

            sendJsonResponse(resp, "success", "Cart updated", cart.size(), CartPrice.calculateTotalPrice(cart));
        } catch (NumberFormatException e) {
            sendJsonResponse(resp, "error", "Invalid input", cart.size(), CartPrice.calculateTotalPrice(cart));
        }
    }

    private void sendJsonResponse(HttpServletResponse resp, String status, String message, int cartSize, double total) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(String.format(
                "{\"status\": \"%s\", \"message\": \"%s\", \"cartSize\": \"%d\", \"totalPrice\": \"%.2f\"}",
                status, message, cartSize, total
        ));
    }

}