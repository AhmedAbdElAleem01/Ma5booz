package com.bakefinity.controller.servlets;

import com.bakefinity.controller.repositories.impls.UserRepoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/uniqueEmail")
public class UniqueEmailServlet extends HttpServlet {
    boolean isEmailUnique(String email){
        UserRepoImpl obj = new UserRepoImpl();
        try {
            return !obj.isFoundEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        PrintWriter out = resp.getWriter();
        if (email == null || email.trim().isEmpty()) {
            out.print("");
        }
        else{
            if(!isEmailUnique(email)){
                out.println("Email is already in use!!!");
            }
            else{
                out.println("");
            }
        }
    }
}