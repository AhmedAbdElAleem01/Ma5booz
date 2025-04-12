package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.CartRepo;
import com.bakefinity.model.entities.CartItem;
import com.bakefinity.model.entities.CartItemId;
import com.bakefinity.utils.ConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartRepoImpl implements CartRepo {

    @Override
    public CartItem get(int userId, int productId) throws Exception {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cartitem WHERE userId = ? AND productId = ?")) {

            stmt.setInt(1, userId);
            stmt.setInt(2, productId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractCartItem(rs);
                }
            }
            return null;
        }
    }

    @Override
    public List<CartItem> getAllByUserId(int userId) throws Exception {
        List<CartItem> cartItems = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cartitem WHERE userId = ?")) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cartItems.add(extractCartItem(rs));
                }
            }
        }
        return cartItems;
    }

    @Override
    public void add(CartItem cartItem) throws Exception {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO cartitem (userId, productId, quantity) " +
                     "VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE quantity = VALUES(quantity)")) {
    
            stmt.setInt(1, cartItem.getId().getUserId());
            stmt.setInt(2, cartItem.getId().getProductId());
            stmt.setInt(3, cartItem.getQuantity());
    
            stmt.executeUpdate();
        }
    }
    

    @Override
    public void update(CartItem cartItem) throws Exception {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE cartitem SET quantity = ? WHERE userId = ? AND productId = ?")) {

            stmt.setInt(1, cartItem.getQuantity());
            stmt.setInt(2, cartItem.getId().getUserId());
            stmt.setInt(3, cartItem.getId().getProductId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int userId, int productId) throws Exception {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM cartitem WHERE userId = ? AND productId = ?")) {

            stmt.setInt(1, userId);
            stmt.setInt(2, productId);

            stmt.executeUpdate();
        }
    }

    @Override
    public void clearUserCart(int userId) throws Exception {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM cartitem WHERE userId = ?")) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
    
    private CartItem extractCartItem(ResultSet rs) throws SQLException {
        CartItem cartItem = new CartItem();
        cartItem.setId(new CartItemId(rs.getInt("productId"), rs.getInt("userId")));
        cartItem.setQuantity(rs.getInt("quantity"));
        return cartItem;
    }
}
