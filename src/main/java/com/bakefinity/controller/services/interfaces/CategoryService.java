package com.bakefinity.controller.services.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.bakefinity.model.dtos.CategoryDTO;
import com.bakefinity.model.entities.Category;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    Category getCategoryById(int id);
    CategoryDTO getCategoryByName(String categoryName) throws SQLException;
}