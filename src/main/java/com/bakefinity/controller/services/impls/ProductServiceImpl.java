package com.bakefinity.controller.services.impls;

import com.bakefinity.controller.repositories.impls.CategoryRepoImpl;
import com.bakefinity.controller.repositories.impls.ProductRepoImpl;
import com.bakefinity.controller.repositories.interfaces.CategoryRepo;
import com.bakefinity.controller.repositories.interfaces.ProductRepo;
import com.bakefinity.controller.services.interfaces.ProductService;
import com.bakefinity.model.entities.Category;
import com.bakefinity.model.dtos.CategoryDTO;
import com.bakefinity.model.dtos.ProductDTO;
import com.bakefinity.model.entities.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private static ProductService instance;
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    private ProductServiceImpl() {
        this.productRepo = new ProductRepoImpl();
        this.categoryRepo = new CategoryRepoImpl();
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
            ProductDTO product = new ProductDTO(productRepo.get(id));
            Category category = categoryRepo.get(product.getCategoryId());
            product.setCategoryName(category.getName());
            return product;
        } catch (Exception e) {
            throw new RuntimeException("failed to obtain Product for ID "+ id + e);
        }
    }

    @Override
    public boolean updateStockQuantity(int productId, int newQuantity) throws SQLException{
        return productRepo.updateStockQuantity(productId, newQuantity);
    }
    // returns all products with the name of the category they belong in
    @Override
    public List<ProductDTO> getAllProductsWithCategoryName() {
        try {
            return productRepo.getAllProducts();
        } catch (Exception e) {
            throw new RuntimeException("failed to get all products with their category name: " + e);
        }
    }

    @Override
    public boolean addNewProduct(String category, String name, String description, String price, String imageName,
            String quantity, String ingredients) throws Exception {
                if (name == null || name.trim().isEmpty()) {
                    System.out.println("Product name is required.");
                    return false;
                }
                if (price == null || !price.matches("\\d+(\\.\\d{1,2})?")) {
                    System.out.println("Invalid price format.");
                    return false;
                }
                if (quantity == null || !quantity.matches("\\d+")) {
                    System.out.println("Quantity must be a number.");
                    return false;
                }
                CategoryDTO inputCategory = categoryRepo.getCategoryByName(category);
                if(inputCategory==null){
                    System.out.println("Input category does not exist");
                    return false;
                }
                int categoryId = inputCategory.getId();
                Double doublePrice = Double.parseDouble(price);
                int intQuantity = Integer.parseInt(quantity);

                if(doublePrice < 0 || intQuantity <0){
                    System.out.println("Invaild input");
                    return false;
                }

                Product product = new Product(categoryId, name, description, doublePrice, imageName, intQuantity, ingredients);
                try {
                    productRepo.add(product);
                    return true;
                } catch (SQLException e) {
                    System.out.println("Database error while adding product: " + e.getMessage());
                    return false;
                }                            
    }

    @Override
    public boolean deleteProduct(int productId) {
        try {
            if(productRepo.get(productId)==null){
                System.out.println("Product does not exist");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database error while retreiving product: " + e.getMessage());
            return false;
        }
        if(productId<=0){
            System.out.println("Invalid product Id");
            return false;
        }
        try {
            productRepo.delete(productId);
            return true;
        } catch (Exception e) {
            System.out.println("Database error while deleting product: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateProduct(ProductDTO product ,String category, String name, String description, String price, String imageName,
    String quantity, String ingredients) throws SQLException{
            if (name == null || name.trim().isEmpty()) {
                System.out.println("Product name is required.");
                return false;
            }
            if (price == null || !price.matches("\\d+(\\.\\d{1,2})?")) {
                System.out.println("Invalid price format.");
                return false;
            }
            if (quantity == null || !quantity.matches("\\d+")) {
                System.out.println("Quantity must be a number.");
                return false;
            }
            CategoryDTO inputCategory = categoryRepo.getCategoryByName(category);
            if(inputCategory==null){
                System.out.println("Input category does not exist");
                return false;
            }
            int categoryId = inputCategory.getId();
            Double doublePrice = Double.parseDouble(price);
            int intQuantity = Integer.parseInt(quantity);
        try {
            if(productRepo.get(product.getId())==null){
                System.out.println("Product does not exist");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database error while retreiving product: " + e.getMessage());
            return false;
        }
        Product newProduct = new Product(product.getId(), categoryId ,name,description,doublePrice,imageName,intQuantity,ingredients);
        try {
            productRepo.update(newProduct);
            return true;
        } catch (Exception e) {
            System.out.println("Database error while updating a product: " + e.getMessage());
            return false;
        }
    }

    public List<ProductDTO> getProductsByCategoryAndPriceRange(int categoryId, double minPrice, double maxPrice, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        try {
            return productRepo.getProductsByCategoryAndPriceRange(categoryId, minPrice, maxPrice, offset, pageSize)
                    .stream()
                    .map(ProductDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve products by category and price range: " + e.getMessage());
        }
    }

    public List<ProductDTO> getProductsByPriceRange(double minPrice, double maxPrice, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        try {
            return productRepo.getProductsByPriceRange(offset, pageSize, minPrice, maxPrice)
                    .stream()
                    .map(ProductDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve products by price range: " + e.getMessage());
        }
    }
    
    public int getTotalProductsByPrice(double minPrice, double maxPrice, Integer categoryId) {
        try {
            return productRepo.getTotalProductsByPrice(minPrice, maxPrice, categoryId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve products by price range: " + e.getMessage());
        }
    }

}