package com.bakefinity.controller.servlets;

import java.io.IOException;

import com.bakefinity.controller.services.impls.ProductServiceImpl;
import com.bakefinity.controller.services.interfaces.ProductService;
import com.bakefinity.model.dtos.ProductDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/product_details")
public class ProductDetailsServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = ProductServiceImpl.getInstance();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("productID");

        if(id != null){
            int productId = Integer.valueOf(id);
            ProductDTO product =  productService.getProductById(productId);
            req.setAttribute("product", product);
            System.out.println(product);
        } 

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/productDetails.jsp");
        dispatcher.forward(req, resp);
    }
}
