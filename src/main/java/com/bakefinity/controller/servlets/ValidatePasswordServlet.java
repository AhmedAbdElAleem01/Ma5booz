package com.bakefinity.controller.servlets;

import com.bakefinity.utils.InputValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/validPassword")
public class ValidatePasswordServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        if (password == null || password.trim().isEmpty()) {
            out.print("");
        }
        else{
            if(!InputValidation.validatePassword(password)){
                out.println("Your password must be at least 5 characters.");
            }
            else{
                out.println("");
            }
        }
    }
}
