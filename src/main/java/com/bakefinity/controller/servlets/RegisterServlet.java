package com.bakefinity.controller.servlets;

import com.bakefinity.controller.repositories.impls.AddressRepoImpl;
import com.bakefinity.controller.repositories.impls.CategoryRepoImpl;
import com.bakefinity.controller.repositories.impls.UserInterestsRepoImpl;
import com.bakefinity.controller.repositories.impls.UserRepoImpl;
import com.bakefinity.model.dtos.Address;
import com.bakefinity.model.dtos.Category;
import com.bakefinity.model.dtos.User;
import com.bakefinity.model.dtos.UserInterests;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    public boolean validatePhone(String phoneNumber) {
        String phonePattern = "^01[0-2,5]\\d{8}$";
        return phoneNumber.matches(phonePattern);
    }

    public boolean validateName(String name) {
        String namePattern = "^[A-Za-z]+(\\s[A-Za-z]+)*$";
        return name.matches(namePattern);
    }

    public boolean validateCityStreet(String name) {
        String namePattern = "^[A-Za-z]+([\\s-][A-Za-z]+)*$";
        return name.matches(namePattern);
    }

    public boolean validatePassword(String password) { // at least 5 chars
        String passwordPattern = "^[A-Za-z\\d@#_.$%^&+=!]{5,}$";
        return password.matches(passwordPattern);
    }

    public  boolean isUsernameAvailable(String username){
        UserRepoImpl userRepoImpl = new UserRepoImpl();
        try {
            return !userRepoImpl.isFoundUsername(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    boolean isEmailUnique(String email){
        UserRepoImpl obj = new UserRepoImpl();
        try {
            return !obj.isFoundEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void forwardWithData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("fname", validateName(req.getParameter("fname")) ? req.getParameter("fname") : "");
        req.setAttribute("lname", validateName(req.getParameter("lname")) ? req.getParameter("lname") : "");
        req.setAttribute("phoneNumber", validatePhone(req.getParameter("phoneNumber")) ? req.getParameter("phoneNumber") : "");
        req.setAttribute("birthdate", req.getParameter("birthdate"));
        req.setAttribute("job", validateName(req.getParameter("job")) ? req.getParameter("job") : "");
        req.setAttribute("email", isEmailUnique(req.getParameter("email")) ? req.getParameter("email") : "");
        req.setAttribute("username", isUsernameAvailable(req.getParameter("username")) ? req.getParameter("username") : "");
        req.setAttribute("password", validatePassword(req.getParameter("password")) ? req.getParameter("password") : "");
        req.setAttribute("creditLimit", req.getParameter("creditLimit"));
        req.setAttribute("country", req.getParameter("country"));
        req.setAttribute("city", validateCityStreet(req.getParameter("city")) ? req.getParameter("city") : "");
        req.setAttribute("street", validateCityStreet(req.getParameter("street")) ? req.getParameter("street") : "");
        req.setAttribute("BNo", req.getParameter("BNo"));
        req.setAttribute("interests", req.getParameterValues("interests"));

        RequestDispatcher rd = req.getRequestDispatcher("/views/user/register.jsp");
        rd.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String phoneNumber = req.getParameter("phoneNumber");
        String birthDateStr = req.getParameter("birthdate");
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date birthDate = null;
        try {
            birthDate = sdf.parse(birthDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String job = req.getParameter("job");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        String creditLimit = req.getParameter("creditLimit");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String buildingNo = req.getParameter("BNo");
        if(!validateName(fname) || !validateName(lname) || !validatePhone(phoneNumber) || !validateName(job) || !validatePassword(password) || !validateCityStreet(city) || !validateCityStreet(street) || !isUsernameAvailable(username) || !isEmailUnique(email)){
            req.setAttribute("error", "Oops.. Some data is not valid, please register with valid data");
            forwardWithData(req, resp);
            return;
        }
        User user = new User(fname + " " + lname, username, phoneNumber, email, password, Double.parseDouble(creditLimit), birthDate, job, LocalDateTime.now());
        UserRepoImpl userRepoImpl = new UserRepoImpl();
        int userId;
        try {
            userId = userRepoImpl.createUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Address address = new Address(userId, Integer.parseInt(buildingNo), street, city, country);
        AddressRepoImpl addressRepoImpl = new AddressRepoImpl();
        try {
            addressRepoImpl.createAddress(address);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String[] interests = req.getParameterValues("interests");
        if (interests != null) {
            for (String interest : interests) {
                CategoryRepoImpl categoryRepoImpl = new CategoryRepoImpl();
                try {
                    Category category = categoryRepoImpl.getCategoryByName(interest);
                    UserInterests userInterests = new UserInterests(userId, category.getId());
                    UserInterestsRepoImpl obj = new UserInterestsRepoImpl();
                    obj.createUserInterests(userInterests);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        resp.sendRedirect("/Ma5booz/views/user/registerConfirmation.jsp");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException { // ensure that username is unique
        String username = req.getParameter("username");
        PrintWriter out = resp.getWriter();
        if (username == null || username.trim().isEmpty()) {
            out.print("");
        }
        else {
            if (!isUsernameAvailable(username)) {
                out.println("Not Available!!!");
            } else {
                out.println("");
            }
        }
    }
}