package com.bakefinity.controller.services.interfaces;

import com.bakefinity.model.dtos.CartDTO;
import java.util.List;

public interface CartService {
    List<CartDTO> getCartItems(Integer userId);
    void addCartItem(CartDTO cartItem);
    void updateCartItem(CartDTO cartItem);
    void removeCartItem(Integer userId, Integer productId);
    void clearCart(Integer userId);
}
