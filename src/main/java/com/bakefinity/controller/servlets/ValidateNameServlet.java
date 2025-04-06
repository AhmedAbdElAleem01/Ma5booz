package com.bakefinity.controller.servlets;

import com.bakefinity.utils.InputValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/validName")
public class ValidateNameServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        PrintWriter out = resp.getWriter();
        if (name == null || name.trim().isEmpty()) {
            out.print("");
        }
        else {
            if (!InputValidation.validateName(name)) {
                out.println("Invalid Name.");
            } else {
                out.println("");
            }
        }
    }
}
