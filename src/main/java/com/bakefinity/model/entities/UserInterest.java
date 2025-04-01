package com.bakefinity.model.entities;

public class UserInterest {
    private int userId;
    private int categoryId;

    public UserInterest() {}
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}