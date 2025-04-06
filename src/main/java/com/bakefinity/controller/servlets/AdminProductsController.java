package com.bakefinity.controller.servlets;

import java.io.IOException;
import java.util.List;

import com.bakefinity.controller.services.impls.CategoryServiceImpl;
import com.bakefinity.controller.services.impls.ProductServiceImpl;
import com.bakefinity.controller.services.interfaces.CategoryService;
import com.bakefinity.controller.services.interfaces.ProductService;
import com.bakefinity.model.dtos.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/products")
public class AdminProductsController extends HttpServlet{
    ProductService productService;
    CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        productService = ProductServiceImpl.getInstance();
        categoryService = CategoryServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
        List<ProductDTO> products = productService.getAllProductsWithCategoryName();
        req.setAttribute("products", products);
        
        req.getRequestDispatcher("/views/admin/products.jsp").forward(req, resp);
    }
}
