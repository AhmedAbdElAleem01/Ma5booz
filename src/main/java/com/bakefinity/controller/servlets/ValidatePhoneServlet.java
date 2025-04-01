package com.bakefinity.controller.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/validPhone")
public class ValidatePhoneServlet extends HttpServlet {
    public boolean validatePhone(String phoneNumber) {
        String phonePattern = "^01[0-2,5]\\d{8}$";
        return phoneNumber.matches(phonePattern);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        PrintWriter out = resp.getWriter();
        if (phone == null || phone.trim().isEmpty()) {
            out.print("");
        }
        else{
            if(!validatePhone(phone)){
                out.println("Invalid Phone Number!!!");
            }
            else{
                out.println("");
            }
        }
    }
}
