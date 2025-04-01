package com.bakefinity.controller.services.interfaces;

import java.util.List;

import com.bakefinity.model.dtos.CategoryDTO;
import com.bakefinity.model.entities.Category;

public interface CategoryService {

    public List<CategoryDTO> getAllCategories();
    public Category getCategoryById(int id);

}