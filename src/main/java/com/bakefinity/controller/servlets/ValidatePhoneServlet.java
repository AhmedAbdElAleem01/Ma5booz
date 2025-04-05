package com.bakefinity.controller.servlets;

import com.bakefinity.utils.InputValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/validPhone")
public class ValidatePhoneServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        PrintWriter out = resp.getWriter();
        if (phone == null || phone.trim().isEmpty()) {
            out.print("");
        }
        else{
            if(!InputValidation.validatePhone(phone)){
                out.println("Invalid Phone Number.");
            }
            else{
                out.println("");
            }
        }
    }
}
