package com.bakefinity.controller.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import com.bakefinity.controller.services.impls.ProfileServiceImpl;
import com.bakefinity.controller.services.interfaces.ProfileService;
import com.bakefinity.model.dtos.*;

import com.bakefinity.utils.Hashing;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    ProfileService profileService;

    @Override
    public void init() throws ServletException {
        profileService = ProfileServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = (UserDTO)req.getSession().getAttribute("user");
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
        AddressDTO address = (AddressDTO)req.getSession().getAttribute("address");
        if (address != null) {
            req.getRequestDispatcher("views/user/profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("views/user/error.jsp?error-message=Could not find user's address");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String form = req.getParameter("form");
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");

        if(user== null){
            resp.sendRedirect("views/user/login.jsp");
            return;
        }
        if("creditLimitForm".equals(form)){
            String creditLimit = req.getParameter("creditLimit");
            Optional<UserDTO> updatedUser = profileService.updateCreditLimit(user , Double.parseDouble(creditLimit));
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

            Optional<UserDTO> updatedUser = profileService.updateShippingInfo(user ,country , city , street , BNo , mobile);
            Optional<AddressDTO> updatedAddress = profileService.getAddress(user.getId());
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

            Optional<UserDTO> updatedUser = profileService.updateAccountDetails(user, username , job , email);
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

            Optional<UserDTO> updatedUser = null;
            try {
                updatedUser = profileService.updatePassword(user, Hashing.getHashedPassword(currentPass) , Hashing.getHashedPassword(newPass) , Hashing.getHashedPassword(confirmPass));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
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