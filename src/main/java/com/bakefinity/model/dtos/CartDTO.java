package com.bakefinity.model.dtos;

import com.bakefinity.model.entities.CartItem;

public class CartDTO {
    private Integer userId;
    private Integer productId;
    private int quantity;

    public CartDTO(Integer userId, Integer productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    

    public CartDTO(CartItem item) {
        this.userId = item.getProductId();
        this.productId = item.getProductId();
        this.quantity = item.getQuantity();
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Integer getProductId() {
        return productId;
    }



    public void setProductId(Integer productId) {
        this.productId = productId;
    }



    @Override
    public String toString() {
        return "CartDTO [userId=" + userId + ", productId=" + productId + ", quantity="
                + quantity + "]";
    }

 
   
    

}
