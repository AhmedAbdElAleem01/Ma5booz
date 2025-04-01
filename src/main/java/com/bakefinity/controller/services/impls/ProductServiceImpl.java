package com.bakefinity.controller.services.impls;

import com.bakefinity.controller.repositories.impls.ProductRepoImpl;
import com.bakefinity.controller.repositories.interfaces.ProductRepo;
import com.bakefinity.controller.services.interfaces.ProductService;
import com.bakefinity.model.dtos.ProductDTO;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private static ProductService instance;
    private final ProductRepo productRepo;

    private ProductServiceImpl() {
        this.productRepo = new ProductRepoImpl();
    }

    public static ProductService getInstance() {
        if (instance == null) {
            synchronized (ProductServiceImpl.class) {
                if (instance == null) {
                    instance = new ProductServiceImpl();
                }
            }
        }
        return instance;
    }

    public List<ProductDTO> getAllProducts() {
        try {
            return productRepo.getAll()
                    .stream()
                    .map(p -> new ProductDTO(p))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve products", e);
        }
    }
  
    public List<ProductDTO> getProductsByCategory(int categoryId) {
        try {
            return productRepo.getByCategory(categoryId)
                    .stream()
                    .map(p -> new ProductDTO(p))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve products for category ID: " + categoryId, e);
        }
    }

    public List<ProductDTO> getProductsByCategoryPage(int categoryId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        try {
            return productRepo.getProductsByCategoryPage(categoryId, offset, pageSize)
                    .stream()
                    .map(p -> new ProductDTO(p))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve products for category ID: " + categoryId, e);
        }
    }
    
    public int getTotalProductsByCategory(int categoryId) {
        try {
            return productRepo.getTotalCountByCategory(categoryId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve products for category ID: " + categoryId, e);
        }
    }

    public List<ProductDTO> getProductsByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        try {
            return productRepo.getProductsByPage(offset, pageSize).
                    stream()
                    .map(p->new ProductDTO(p))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve products by page: " +  e);
        }
    }
    
    public int getTotalProductCount() {
        try {
            return productRepo.getTotalCount();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve products for category ID: " + e);
        }
    }
    


    public List<ProductDTO> getClassicProducts(int limit) {
        try {
           return productRepo.getTopInStock(limit)
                .stream()
                .map(p -> new ProductDTO(p.getName(), p.getDescription(),  p.getPrice(), p.getImageUrl()))
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("failed to obtain classic Products"+ e);
        }
    }

    public List<ProductDTO> searchProductsByName(String name) {
        try {
           return productRepo.searchByName(name)
                .stream()
                .map(p -> new ProductDTO(p))
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("failed to obtain searched Products"+ e);
        }
    }

    public ProductDTO getProductById(int id){
        try {
            return new ProductDTO(productRepo.get(id));
        } catch (Exception e) {
            throw new RuntimeException("failed to obtain Product for ID "+ id + e);
        }
    }

    

}