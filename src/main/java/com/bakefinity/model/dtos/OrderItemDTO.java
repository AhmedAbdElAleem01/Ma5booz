package com.bakefinity.model.dtos;


public class OrderItemDTO {
    private int id;
    private int orderId;
    private Integer productId;
    private String productName;
    private double price;
    private int quantity;

    public OrderItemDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public Integer getProductId() { return productId; }
    public String getProductName() { return this.productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public double getPrice() { return this.price; }
    public void setPrice(double price) { this.price = price;}
    public void setProductId(Integer productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}