package com.bakefinity.controller.servlets;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/editProduct")
public class EditProductServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
        System.out.println("EditProductServlet Triggered");
        req.getRequestDispatcher("/views/admin/editProduct.jsp").forward(req, resp);
    }
}
