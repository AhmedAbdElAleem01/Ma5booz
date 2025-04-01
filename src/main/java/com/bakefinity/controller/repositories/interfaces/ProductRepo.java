package com.bakefinity.controller.repositories.interfaces;

import java.util.List;

import com.bakefinity.model.entities.Product;

public interface ProductRepo extends BaseRepo<Product>{

    List<Product> getByCategory(int categoryId) throws Exception;

    List<Product> getTopInStock(int limit) throws Exception;

    List<Product> searchByName(String name) throws Exception;

    List<Product> getProductsByPage(int offset, int limit) throws Exception;
    List<Product> getProductsByCategoryPage(int categoryId, int offset, int limit) throws Exception;

    int getTotalCount() throws Exception;
    int getTotalCountByCategory(int categoryId) throws Exception;

}