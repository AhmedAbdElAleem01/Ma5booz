package com.bakefinity.controller.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.bakefinity.model.dtos.CategoryDTO;
import com.bakefinity.model.entities.Category;

public interface CategoryRepo extends BaseRepo<Category>{

    List<Category> searchByName(String name) throws Exception;
    boolean createCategory(CategoryDTO category) throws SQLException;
    CategoryDTO getCategoryByName(String categoryName) throws SQLException;

}