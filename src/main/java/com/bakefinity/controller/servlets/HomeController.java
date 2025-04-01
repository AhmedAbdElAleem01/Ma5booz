package com.bakefinity.controller.servlets;

import java.io.IOException;
import java.util.List;

import com.bakefinity.controller.services.impls.CategoryServiceImpl;
import com.bakefinity.controller.services.impls.ProductServiceImpl;
import com.bakefinity.controller.services.interfaces.CategoryService;
import com.bakefinity.controller.services.interfaces.ProductService;
import com.bakefinity.model.dtos.CategoryDTO;
import com.bakefinity.model.dtos.ProductDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/home")
public class HomeController extends HttpServlet {
    private ProductService productService;
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        productService = ProductServiceImpl.getInstance();
        categoryService = CategoryServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDTO> classicProducts = productService.getClassicProducts(8);

        List<CategoryDTO> homeCategories = categoryService.getAllCategories();

        req.setAttribute("classicProducts", classicProducts);
        req.setAttribute("homeCategories", homeCategories);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/home.jsp");
        dispatcher.forward(req, resp);
    }
}
