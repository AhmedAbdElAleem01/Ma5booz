package com.bakefinity.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.bakefinity.controller.services.impls.ProductServiceImpl;
import com.bakefinity.controller.services.interfaces.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/deleteProduct")
public class AdminDeleteProductController extends HttpServlet{
    ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = ProductServiceImpl.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
        PrintWriter out = resp.getWriter();

        int productId = Integer.parseInt(req.getParameter("id"));
        boolean deleted = productService.deleteProduct(productId);
        String response = (deleted)? "success" : "fail";
        out.write(response);
    }
}
