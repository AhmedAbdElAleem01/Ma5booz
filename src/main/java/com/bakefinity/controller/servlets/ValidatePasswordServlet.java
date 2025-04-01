package com.bakefinity.controller.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/validPassword")
public class ValidatePasswordServlet extends HttpServlet {
    public boolean validatePassword(String password) { // at least 5 chars
        String passwordPattern = "^[A-Za-z\\d@#_.$%^&+=!]{5,}$";
        return password.matches(passwordPattern);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        if (password == null || password.trim().isEmpty()) {
            out.print("");
        }
        else{
            if(!validatePassword(password)){
                out.println("Your password must be at least 5 characters!!!");
            }
            else{
                out.println("");
            }
        }
    }
}
