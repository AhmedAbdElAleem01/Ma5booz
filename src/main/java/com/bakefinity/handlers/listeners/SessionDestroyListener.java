package com.bakefinity.handlers.listeners;


import java.util.Map;

import com.bakefinity.controller.services.impls.CartServiceImpl;
import com.bakefinity.controller.services.interfaces.CartService;
import com.bakefinity.model.dtos.CartDTO;
import com.bakefinity.model.dtos.UserDTO;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionDestroyListener implements HttpSessionListener {

    private final CartService cartService = CartServiceImpl.getInstance();
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("Session destroyed: " + event.getSession().getId());
        HttpSession session = event.getSession();

        @SuppressWarnings("unchecked")
        Map<Integer, CartDTO> cart = (Map<Integer, CartDTO>) session.getAttribute("cart");
        if (cart == null) return;

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            System.out.println("No user found in session. Skipping cart persistence.");
            return;
        }

        try {
            Integer userId = user.getId();
            cartService.clearCart(userId);
            System.out.println("cleared");
            System.out.println(cart);
            for (CartDTO cartItem : cart.values()) {
                cartService.addCartItem(cartItem);
            }
        } catch (Exception e) {
            System.err.println("Error saving cart items: " + e.getMessage());
        }
    }
}
    