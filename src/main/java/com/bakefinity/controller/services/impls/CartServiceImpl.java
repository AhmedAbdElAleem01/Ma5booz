package com.bakefinity.controller.services.impls;

import com.bakefinity.controller.repositories.impls.CartRepoImpl;
import com.bakefinity.controller.repositories.interfaces.CartRepo;
import com.bakefinity.controller.services.interfaces.CartService;
import com.bakefinity.model.dtos.CartDTO;
import com.bakefinity.model.entities.CartItem;
import com.bakefinity.model.entities.CartItemId;

import java.util.List;
import java.util.stream.Collectors;

public class CartServiceImpl implements CartService {
    private static CartService instance;
    private final CartRepo cartRepo;

    private CartServiceImpl() {
        this.cartRepo = new CartRepoImpl();
    }

    public static CartService getInstance() {
        if (instance == null) {
            synchronized (CartServiceImpl.class) {
                if (instance == null) {
                    instance = new CartServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<CartDTO> getCartItems(Integer userId) {
        try {
            List<CartItem> cartItems = cartRepo.getAllByUserId(userId);
            return cartItems.stream()
                    .map(cartItem -> new CartDTO(new CartItemId(cartItem.getId().getProductId(), cartItem.getId().getUserId()), cartItem.getQuantity()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve cart items", e);
        }
    }

    @Override
    public void addCartItem(CartDTO cartItem) {
        try {
            CartItem entity = new CartItem();
            entity.setId(new CartItemId(cartItem.getProductId(), cartItem.getUserId()));
            entity.setQuantity(cartItem.getQuantity());
            cartRepo.add(entity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add cart item", e);
        }
    }

    @Override
    public void updateCartItem(CartDTO cartItem) {
        try {
            CartItem entity = new CartItem();
            entity.setId(new CartItemId(cartItem.getProductId(), cartItem.getUserId()));
            entity.setQuantity(cartItem.getQuantity());
            cartRepo.update(entity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update cart item", e);
        }
    }

    @Override
    public void removeCartItem(Integer userId, Integer productId) {
        try {
            cartRepo.delete(userId, productId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to remove cart item", e);
        }
    }

    @Override
    public void clearCart(Integer userId) {
        try {
            cartRepo.clearUserCart(userId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear cart", e);
        }
    }
}
