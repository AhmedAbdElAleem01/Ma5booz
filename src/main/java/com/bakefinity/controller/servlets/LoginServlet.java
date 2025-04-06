package com.bakefinity.controller.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.bakefinity.controller.services.impls.ProfileServiceImpl;
import com.bakefinity.controller.services.impls.UserLoginServiceImpl;
import com.bakefinity.controller.services.interfaces.ProfileService;
import com.bakefinity.controller.services.interfaces.UserLoginService;
import com.bakefinity.model.dtos.*;
import com.bakefinity.controller.services.impls.CartServiceImpl;
import com.bakefinity.controller.services.interfaces.CartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    UserLoginService userLoginService = new UserLoginServiceImpl();
    ProfileService profileService = ProfileServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = (UserDTO)req.getSession().getAttribute("user");
        boolean alreadyLoggedIn = (user!=null);


        if(alreadyLoggedIn){
            redirectUser(req ,resp, user);
            return;
        }
        resp.sendRedirect("views/user/login.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");

        Optional<UserDTO> user = userLoginService.login(email, password);
        if (user.isPresent()) {
            req.getSession().setAttribute("user", user.get());
            Optional<AddressDTO> address = profileService.getAddress(user.get().getId());
            if (address.isPresent()) {
                req.getSession().setAttribute("address", address.get());
            } else {
                resp.sendRedirect("views/user/error.jsp?error-message=Could not find user's address");
            }

            loadCart(req, user);
            if ( rememberMe != null ) {
                String emailPasswordCookieString = user.get().getEmail() + "+" + user.get().getPassword();
                Cookie rememberMeCookie = new Cookie( "rememberMeCookie", emailPasswordCookieString );
                resp.addCookie( rememberMeCookie );
            }
            redirectUser(req ,resp, user.get());
        }else{
            resp.sendRedirect("views/user/login.jsp?error-message=Invalid email or password");
        }
    }
    private void redirectUser(HttpServletRequest request , HttpServletResponse response, UserDTO user) throws IOException{
        // redirect user based on his role
        if ("ADMIN".equals(user.getRole())) {
            response.sendRedirect("views/admin/home.jsp");
        } else {
            response.sendRedirect(getServletContext().getContextPath()+"/shop");
        }
    }


    private void loadCart(HttpServletRequest req, Optional<UserDTO> userOpt) {
        if (userOpt.isEmpty()) return;

        HttpSession session = req.getSession();
        UserDTO user = userOpt.get();

        @SuppressWarnings("unchecked")
        Map<Integer, CartDTO> cart = (Map<Integer, CartDTO>) session.getAttribute("cart");

        if (cart != null) {
            for (CartDTO cartItem : cart.values()) {
                if (cartItem.getUserId() == null) {
                    cartItem.setUserId(user.getId());
                }
            }
        } else {
            cart = new HashMap<>();
        }

        CartService cartService = CartServiceImpl.getInstance();

        try {
            List<CartDTO> dbCartItems = cartService.getCartItems(user.getId());
            if (dbCartItems != null) {
                for (CartDTO item : dbCartItems) {
                    cart.putIfAbsent(item.getProductId(), item);
                }
                session.setAttribute("cart", cart);
            }
        } catch (Exception e) {
            System.err.println("Error loading cart items: " + e.getMessage());
        }
    }

}