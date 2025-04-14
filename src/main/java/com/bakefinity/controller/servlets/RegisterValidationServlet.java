package com.bakefinity.controller.servlets;

import com.bakefinity.controller.services.impls.UserRegisterServiceImpl;
import com.bakefinity.controller.services.interfaces.UserRegisterService;
import com.bakefinity.utils.InputValidation;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/valid")
public class RegisterValidationServlet extends HttpServlet {
    UserRegisterService userRegisterService = new UserRegisterServiceImpl();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (String param : parameterMap.keySet()) {
            String value = req.getParameter(param);
            if(param.equals("Name") || param.equals("Email") || param.equals("Phone") || param.equals("Password") || param.equals("Username") || param.equals("Credit")) {
                if (value == null || value.trim().isEmpty()) {
                    out.println(param + " is required.");
                    continue;
                }
            }
            switch (param) {
                case "Name":
                    if (!InputValidation.validateName(value)) {
                        out.println("Invalid Name.");
                    }
                    break;
                case "Job":
                    if (value != null && !value.trim().isEmpty() && !InputValidation.validateName(value)) {
                        out.println("Invalid Job Name.");
                    }
                    break;
                case "Password":
                    if(!InputValidation.validatePassword(value)){
                        out.println("Your password must be at least 5 characters.");
                    }
                    break;
                case "Email":
                    try {
                        if(!userRegisterService.isEmailUnique(value)){
                            out.println("Email is already in use.");
                        }
                        else if (!InputValidation.validateEmail(value)) {
                            out.println("Invalid Email.");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "Phone":
                    if (!InputValidation.validatePhone(value)){
                        out.println("Invalid Phone Number.");
                    }
                    break;
                case "Username":
                    try {
                        if (!userRegisterService.isUsernameAvailable(value)) {
                            out.println("Username is already taken.");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "City":
                    if (value != null && !value.trim().isEmpty() && !InputValidation.validateCityStreet(value)){
                        out.println("Invalid City Name.");
                    }
                    break;
                case "Street":
                    if (value != null && !value.trim().isEmpty() && !InputValidation.validateCityStreet(value)){
                        out.println("Invalid Street Name.");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}