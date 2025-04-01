package com.bakefinity.controller.repositories.interfaces;

import com.bakefinity.model.dtos.Category;

import java.sql.SQLException;

public interface CategoryRepo {
    boolean createCategory(Category category) throws SQLException;
    Category getCategoryByName(String categoryName) throws SQLException;
}