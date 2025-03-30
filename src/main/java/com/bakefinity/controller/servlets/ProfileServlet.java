package com.bakefinity.controller.servlets;

import java.io.IOException;
import java.util.Optional;
import com.bakefinity.controller.services.impls.ProfileServiceImpl;
import com.bakefinity.controller.services.interfaces.ProfileService;
import com.bakefinity.model.entities.Address;
import com.bakefinity.model.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    ProfileService profileService = new ProfileServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user== null){
            resp.sendRedirect("views/user/login.jsp");
            return;
        }
        String username = req.getParameter("username");
        if(username!=null){
            boolean isTaken = profileService.isUsernameTaken(username); 
            resp.setContentType("text/plain");
            resp.getWriter().write(isTaken ? "taken" : "available");
            return;
        }
        Optional<Address> address = profileService.getAddress(user.getId());
        if (address.isPresent()) {
            req.getSession().setAttribute("address", address.get());  
            req.getRequestDispatcher("views/user/profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("views/user/error.jsp?error-message=Could not find user's address");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String form = req.getParameter("form");
        User user = (User) req.getSession().getAttribute("user");

        if(user== null){
            resp.sendRedirect("views/user/login.jsp");
            return;
        }
        if("creditLimitForm".equals(form)){
            String creditLimit = req.getParameter("creditLimit");
            Optional<User> updatedUser = profileService.updateCreditLimit(user , creditLimit);
            if(updatedUser.isPresent())
                req.getSession().setAttribute("user", updatedUser.get()); //update user
            else{
                resp.sendRedirect("views/user/error.jsp?error-message=Could not update user's credit Limit. Try again.");
                return;
            }
        }
        else if("addressForm".equals(form)){
            String country = req.getParameter("country");
            String city = req.getParameter("city");
            String street = req.getParameter("street");
            String BNo = req.getParameter("BNo");
            String mobile = req.getParameter("mobile");

            Optional<User> updatedUser = profileService.updateShippingInfo(user ,country , city , street , BNo , mobile);
            Optional<Address> updatedAddress = profileService.getAddress(user.getId());
            if (updatedAddress.isPresent()) {
                req.getSession().setAttribute("address", updatedAddress.get());  //update address
            } else {
                resp.sendRedirect("views/user/error.jsp?error-message=Could not find user's updated address");
                return;
            }
            if (updatedUser.isPresent()) 
                req.getSession().setAttribute("user", updatedUser.get()); //update user
            else{
                resp.sendRedirect("views/user/error.jsp?error-message=Could not update user's shipping information. Try again.");
                return;
            }
        }
        else if("accountForm".equals(form)){
            String username = req.getParameter("username");
            String job = req.getParameter("job");
            String email = req.getParameter("email");

            Optional<User> updatedUser = profileService.updateAccountDetails(user, username , job , email);
            if(updatedUser.isPresent()){
                deleteOldCookie(req, resp);
                req.getSession().setAttribute("user", updatedUser.get());
            }else{
                resp.sendRedirect("views/user/error.jsp?error-message=Could not update user's account details. Try again.");
                return;
            }
        }
        else if("passwordForm".equals(form)){
            String currentPass = req.getParameter("currentPass");
            String newPass = req.getParameter("newPass");
            String confirmPass = req.getParameter("confirmPass");

            Optional<User> updatedUser = profileService.updatePassword(user, currentPass , newPass , confirmPass);
            if(updatedUser.isPresent()){
                req.getSession().setAttribute("user", updatedUser.get());
                deleteOldCookie(req , resp);

                // sign in again with new password
                req.getRequestDispatcher("views/user/login.jsp").forward(req, resp);
                return;
            }else{
                resp.sendRedirect("views/user/error.jsp?error-message=Could not update user's password. Try again.");
                return;
            }
        }
        resp.sendRedirect("views/user/profile.jsp");
    }
        
    private void deleteOldCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("rememberMeCookie")){
                cookie.setMaxAge( 0 );   //delete cookie
                resp.addCookie( cookie );
            }
        }
    }
}
