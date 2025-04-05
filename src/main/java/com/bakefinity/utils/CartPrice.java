package com.bakefinity.utils;

import com.bakefinity.controller.services.impls.ProductServiceImpl;
import com.bakefinity.model.dtos.CartDTO;
import com.bakefinity.model.dtos.ProductDTO;
import java.util.Map;

public class CartPrice {
    public static double calculateTotalPrice(Map<Integer, CartDTO> cart) {
        double totalPrice = 0;
        for (CartDTO cartItem : cart.values()) {
            ProductDTO product = ProductServiceImpl.getInstance().getProductById(cartItem.getProductId());
            if (product != null) {
                totalPrice += product.getPrice() * cartItem.getQuantity();
            }
        }
        return Math.round(totalPrice * 100.0) / 100.0;
    }
}
