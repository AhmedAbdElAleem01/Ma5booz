package com.bakefinity.controller.servlets;

import com.bakefinity.controller.services.impls.UserRegisterServiceImpl;
import com.bakefinity.controller.services.interfaces.UserRegisterService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/uniqueUsername")
public class UniqueUsernameServlet extends HttpServlet {
    UserRegisterService userRegisterService = new UserRegisterServiceImpl();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        PrintWriter out = resp.getWriter();
        if (username == null || username.trim().isEmpty()) {
            out.print("");
        }
        else {
            try {
                if (!userRegisterService.isUsernameAvailable(username)) {
                    out.println("Username is already taken.");
                } else {
                    out.println("");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}