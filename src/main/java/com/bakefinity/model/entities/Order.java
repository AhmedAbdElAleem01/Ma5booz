package com.bakefinity.model.entities;

import java.time.LocalDateTime;

import com.bakefinity.model.enums.OrderStatus;
import com.bakefinity.model.enums.PaymentMethod;

public class Order {
    private int id;
    private int userId;
    private Double totalCost;
    private PaymentMethod paymentMethod;
    private LocalDateTime orderedAt;
    private OrderStatus status;

    public Order() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Double getTotalCost() { return totalCost; }
    public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public LocalDateTime getOrderedAt() { return orderedAt; }
    public void setOrderedAt(LocalDateTime orderedAt) { this.orderedAt = orderedAt; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}