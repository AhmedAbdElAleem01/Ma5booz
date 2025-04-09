package com.bakefinity.controller.servlets;

import com.bakefinity.controller.services.impls.OrderItemServiceImpl;
import com.bakefinity.controller.services.impls.OrderServiceImpl;
import com.bakefinity.controller.services.impls.ProductServiceImpl;
import com.bakefinity.controller.services.impls.ProfileServiceImpl;
import com.bakefinity.controller.services.interfaces.OrderItemService;
import com.bakefinity.controller.services.interfaces.OrderService;
import com.bakefinity.controller.services.interfaces.ProductService;
import com.bakefinity.controller.services.interfaces.ProfileService;
import com.bakefinity.model.dtos.CartDTO;
import com.bakefinity.model.dtos.ProductDTO;
import com.bakefinity.model.dtos.UserDTO;
import com.bakefinity.model.entities.Order;
import com.bakefinity.model.entities.OrderItem;
import com.bakefinity.model.enums.OrderStatus;
import com.bakefinity.model.enums.PaymentMethod;
import com.bakefinity.utils.CartPrice;
import com.bakefinity.utils.EmailUtil;
import com.bakefinity.utils.InputValidation;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private OrderItemService orderItemService;
    private OrderService orderService;
    private ProfileService profileService;
    private ProductService productService;

    @Override
    public void init(){
        orderItemService = OrderItemServiceImpl.getInstance();
        orderService = OrderServiceImpl.getInstance();
        profileService = ProfileServiceImpl.getInstance();
        productService = ProductServiceImpl.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");
        System.out.println(req.getAttribute("products"));

        if (user == null) {
            resp.sendRedirect("views/user/login.jsp");
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/checkout.jsp");
        dispatcher.forward(req, resp);
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // check billing details:
        String phoneNumber = req.getParameter("phoneNumber");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        HttpSession session = req.getSession(false);
        @SuppressWarnings("unchecked")
        Map<Integer, CartDTO> cart = (Map<Integer, CartDTO>) session.getAttribute("cart");
        if(!InputValidation.validatePhone(phoneNumber) || !InputValidation.validateCityStreet(city) || !InputValidation.validateCityStreet(street)){
            Map<Integer, ProductDTO> products = new HashMap<>();
            for (CartDTO cartItem : cart.values()) {
                ProductDTO product = productService.getProductById(cartItem.getProductId());
                if (product != null) {
                    products.put(cartItem.getProductId(), product);
                }
            }
            req.setAttribute("products", products);
            req.setAttribute("totalPrice", CartPrice.calculateTotalPrice(cart));
            req.setAttribute("error", "Oops.. Some of billing details are not valid, please enter valid data and try again.");
            RequestDispatcher rd = req.getRequestDispatcher("/views/user/checkout.jsp");
            rd.forward(req, resp);
            return;
        }

        // create order
        for(CartDTO cartItem : cart.values()){
            int productId = cartItem.getProductId();
            if(productService.getProductById(productId).getStockQuantity() < cartItem.getQuantity()){
                req.getSession().setAttribute("error", "Sorry, the requested quantity exceeds the available stock for some products. Please adjust your order accordingly.");
                resp.sendRedirect("cart");
                return;     
            }
        }
        double totalCost = CartPrice.calculateTotalPrice(cart);
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");
        if(user.getCreditLimit() < totalCost){
            req.getSession().setAttribute("error", "The total cost of your card exceeds your credit limit. Please remove some items or increase your credit to proceed.");
            resp.sendRedirect("cart");
            return;
        }
        Order order = new Order(user.getId(), totalCost, PaymentMethod.CREDIT_CARD, LocalDateTime.now(), OrderStatus.SHIPPED);
        int newOrderId = 0;
        try {
            newOrderId = orderService.create(order);
        } catch (SQLException e) {
            try {
                orderService.updateStatus(newOrderId, OrderStatus.FAILED);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            req.setAttribute("error", "There was an issue processing your order. Please try again.");
            RequestDispatcher rd = req.getRequestDispatcher("/views/user/checkout.jsp");
            rd.forward(req, resp);
            return;
        }

        // update user's credit limit
        Optional<UserDTO> updatedUser = profileService.updateCreditLimit(user, user.getCreditLimit() - totalCost);
        if(updatedUser.isPresent()) {
            req.getSession().setAttribute("user", updatedUser.get()); // update user
        }
        else{
            try {
                orderService.updateStatus(newOrderId, OrderStatus.FAILED);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("error", "There was an issue processing your order. Please try again.");
            RequestDispatcher rd = req.getRequestDispatcher("/views/user/checkout.jsp");
            rd.forward(req, resp);
            return;
        }

        // update stock quantity
        for(CartDTO cartItem : cart.values()){
            int productId = cartItem.getProductId();
            int newStockQuantity = productService.getProductById(productId).getStockQuantity() - cartItem.getQuantity();
            try {
                productService.updateStockQuantity(productId, newStockQuantity);
            } catch (SQLException e) {
                try {
                    orderService.updateStatus(newOrderId, OrderStatus.FAILED);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                req.setAttribute("error", "There was an issue processing your order. Please try again.");
                RequestDispatcher rd = req.getRequestDispatcher("/views/user/checkout.jsp");
                rd.forward(req, resp);
                return;
            }
        }

        // create row in orderItems table
        for(CartDTO cartItem : cart.values()){
            try {
                orderItemService.create(new OrderItem(newOrderId, cartItem.getProductId(), cartItem.getQuantity()));
            } catch (SQLException e) {
                try {
                    orderService.updateStatus(newOrderId, OrderStatus.FAILED);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                req.setAttribute("error", "There was an issue processing your order. Please try again.");
                RequestDispatcher rd = req.getRequestDispatcher("/views/user/checkout.jsp");
                rd.forward(req, resp);
                return;
            }
        }

        // send email
        StringBuilder orderDetails = new StringBuilder();
        for(CartDTO cartItem : cart.values()){
            int productId = cartItem.getProductId();
            ProductDTO curProduct = productService.getProductById(productId);
            orderDetails.append("Product Name: " + curProduct.getName() + "\nPrice: " + curProduct.getPrice() + "\nQuantity: " + cartItem.getQuantity() + "\n");
        }
        orderDetails.append("Total Cost: " + totalCost + " EGP");
        EmailUtil.sendOrderConfirmationEmail(user.getEmail(), user.getName(), orderDetails.toString());

        // clear the cart map
        cart.clear();

        resp.sendRedirect("views/user/confirmation.jsp");
    }
}