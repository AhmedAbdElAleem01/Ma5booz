package com.bakefinity.model.dtos;

public class UserInterests {
    private int userId;
    private int categoryId;

    public UserInterests() {
    }

    public UserInterests(int userId, int categoryId) {
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "UserInterests{" +
                "userId=" + userId +
                ", categoryId=" + categoryId +
                '}';
    }
}
