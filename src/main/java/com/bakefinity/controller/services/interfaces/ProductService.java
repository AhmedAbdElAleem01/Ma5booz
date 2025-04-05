package com.bakefinity.controller.services.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.bakefinity.model.dtos.ProductDTO;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getProductsByCategory(int categoryId);

    List<ProductDTO> getClassicProducts(int limit) ;
    List<ProductDTO> searchProductsByName(String name);

    ProductDTO getProductById(int id);

    List<ProductDTO> getProductsByCategoryPage(int categoryId, int page, int pageSize);
    int getTotalProductsByCategory(int categoryId);

    List<ProductDTO> getProductsByPage(int page, int pageSize);
    int getTotalProductCount();

    boolean updateStockQuantity(int productId, int newQuantity) throws SQLException;
}