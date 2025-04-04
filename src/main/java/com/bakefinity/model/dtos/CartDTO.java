package com.bakefinity.model.dtos;

public class CartDTO {
    private Integer userId;
    private String sessionId;
    private Integer productId;
    private int quantity;

    public CartDTO(Integer userId, String sessionId, Integer productId, int quantity) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
        return "CartDTO [userId=" + userId + ", sessionId=" + sessionId + ", productId=" + productId + ", quantity="
                + quantity + "]";
    }

 
   
    

}
