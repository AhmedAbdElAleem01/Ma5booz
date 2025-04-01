package com.bakefinity.controller.servlets;

import java.io.IOException;
import java.util.Optional;
import com.bakefinity.controller.services.impls.UserLoginServiceImpl;
import com.bakefinity.controller.services.interfaces.UserLoginService;
import com.bakefinity.model.dtos.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    UserLoginService userLoginService = new UserLoginServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        boolean alreadyLoggedIn = (user!=null);

        if(alreadyLoggedIn){
            redirectUser(resp, user);
            return;
        }       
        resp.sendRedirect("views/user/login.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");

        Optional<User> user = userLoginService.login(email, password);
        if (user.isPresent()) {
            req.getSession().setAttribute("user", user.get());

            if ( rememberMe != null ) {
                String emailPasswordCookieString = user.get().getEmail() + "+" + user.get().getPassword();
                Cookie rememberMeCookie = new Cookie( "rememberMeCookie", emailPasswordCookieString );
                resp.addCookie( rememberMeCookie );
            }
            redirectUser(resp, user.get());
        }else{
            resp.sendRedirect("views/user/login.jsp?error-message=Invalid email or password");
        }      
    }
    private void redirectUser(HttpServletResponse resp , User user) throws IOException{
        // redirect user based on his role
        if ("ADMIN".equals(user.getRole())) {
            resp.sendRedirect("views/admin/admin.jsp");
        } else {
            resp.sendRedirect("views/user/shop.jsp");
        } 
    }
}
