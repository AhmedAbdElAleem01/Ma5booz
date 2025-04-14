package com.bakefinity.controller.servlets;
import java.io.IOException;
import java.util.List;

import com.bakefinity.controller.services.impls.OrderServiceImpl;
import com.bakefinity.controller.services.interfaces.OrderService;
import com.bakefinity.model.dtos.OrderItemDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/orderDetails")
public class AdminViewOrderDetailsController extends HttpServlet{
    OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = OrderServiceImpl.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
        int orderId = Integer.parseInt(req.getParameter("id"));

        List<OrderItemDTO> orderItems = orderService.getOrderItemByOrderId(orderId);
        req.setAttribute("orderItems", orderItems);
        req.getRequestDispatcher("/views/admin/orderDetails.jsp").forward(req, resp);
    }
}