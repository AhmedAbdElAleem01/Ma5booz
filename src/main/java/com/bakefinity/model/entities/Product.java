package com.bakefinity.model.entities;


public class Product {
    private int id;
    private int categoryId;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private int stockQuantity;
    private String ingredients;

    public Product() {}

    public Product(int categoryId, String name, String description, Double price, String imageUrl,
            int stockQuantity, String ingredients) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stockQuantity = stockQuantity;
        this.ingredients = ingredients;
    }

    public Product(int id, int categoryId, String name, String description, Double price, String imageUrl,
            int stockQuantity, String ingredients) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stockQuantity = stockQuantity;
        this.ingredients = ingredients;
    }



    @Override
    public String toString() {
        return "Product [name=" + name + ", description=" + description + ", price=" + price + ", stockQuantity="
                + stockQuantity + "]";
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }
}