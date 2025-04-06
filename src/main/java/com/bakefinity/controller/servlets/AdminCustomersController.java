package com.bakefinity.controller.servlets;

import java.io.IOException;
import java.util.List;

import com.bakefinity.controller.services.impls.CustomerServiceImpl;
import com.bakefinity.controller.services.interfaces.CustomerService;
import com.bakefinity.model.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/customers")
public class AdminCustomersController extends HttpServlet{
    CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = CustomerServiceImpl.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
        List<User> customers = customerService.getAllCustomers();
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/views/admin/customers.jsp").forward(req, resp);
    }
}
