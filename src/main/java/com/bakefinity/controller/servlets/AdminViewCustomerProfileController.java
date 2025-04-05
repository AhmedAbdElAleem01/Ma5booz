package com.bakefinity.controller.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.bakefinity.controller.services.impls.OrderServiceImpl;
import com.bakefinity.controller.services.impls.ProfileServiceImpl;
import com.bakefinity.controller.services.interfaces.OrderService;
import com.bakefinity.controller.services.interfaces.ProfileService;
import com.bakefinity.model.dtos.AddressDTO;
import com.bakefinity.model.dtos.UserDTO;
import com.bakefinity.model.entities.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/customerProfile")
public class AdminViewCustomerProfileController extends HttpServlet{   
    ProfileService profileService;
    OrderService orderService;

    @Override
    public void init() throws ServletException {
        profileService = ProfileServiceImpl.getInstance();
        orderService = OrderServiceImpl.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
        int customerId = Integer.parseInt(req.getParameter("id"));

        Optional<UserDTO> customer = profileService.getUserProfile(customerId);
        Optional<AddressDTO> customerAddress = profileService.getAddress(customerId);
        List<Order> orders = orderService.getAllOrdersByCustomerId(customerId);

        if(customer.isEmpty() || customerAddress.isEmpty()){
            req.getRequestDispatcher("/views/admin/error.jsp?error-message=Could not retreive this customer's profile. Try Again.");
            return;
        }
        AddressDTO addressDTO = customerAddress.get();
        String address = addressDTO.getCity()+","+addressDTO.getStreet()+","+addressDTO.getBuildingNo();

        req.setAttribute("customer", customer.get());
        req.setAttribute("address", address);
        req.setAttribute("orders", (orders.isEmpty())? "No Orders Yet " : orders);

        req.getRequestDispatcher("/views/admin/customerProfile.jsp").forward(req, resp);
    }
}
