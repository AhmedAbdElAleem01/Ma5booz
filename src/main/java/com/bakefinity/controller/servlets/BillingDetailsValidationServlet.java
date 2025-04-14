package com.bakefinity.controller.servlets;

import com.bakefinity.utils.InputValidation;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/validData")
public class BillingDetailsValidationServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (String param : parameterMap.keySet()) {
            String value = req.getParameter(param);
            if(param.equals("Phone") || param.equals("City") || param.equals("Street") || param.equals("BuildingNo")) {
                if (value == null || value.trim().isEmpty()) {
                    if(param.equals("BuildingNo")){
                        out.println("Building Number" + " is required.");
                    }
                    else {
                        out.println(param + " is required.");
                    }
                    continue;
                }
            }
            switch (param) {
                case "Phone":
                    if (!InputValidation.validatePhone(value)){
                        out.println("Invalid Phone Number.");
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
