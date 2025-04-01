package com.bakefinity.controller.services.impls;

import com.bakefinity.controller.repositories.impls.CategoryRepoImpl;
import com.bakefinity.controller.repositories.interfaces.CategoryRepo;
import com.bakefinity.controller.services.interfaces.CategoryService;
import com.bakefinity.model.dtos.CategoryDTO;
import com.bakefinity.model.entities.Category;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {
    private static CategoryService instance;
    private final CategoryRepo categoryRepo;

    private CategoryServiceImpl() {
        this.categoryRepo = new CategoryRepoImpl();
    }

    public static CategoryService getInstance() {
        if (instance == null) {
            synchronized (CategoryServiceImpl.class) {
                if (instance == null) {
                    instance = new CategoryServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        try {
            return categoryRepo.getAll()
                    .stream()
                    .map(cat -> new CategoryDTO(cat.getId(),cat.getName(), cat.getDescription(), cat.getImageUrl()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve categories", e);
        }
    }

    @Override
    public Category getCategoryById(int id) {
        try {
            return categoryRepo.get(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve category with ID: " + id, e);
        }
    }

}