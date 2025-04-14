package com.bakefinity.controller.servlets;

import com.bakefinity.controller.services.impls.AddressServiceImpl;
import com.bakefinity.controller.services.impls.CategoryServiceImpl;
import com.bakefinity.controller.services.impls.UserInterestsServiceImpl;
import com.bakefinity.controller.services.impls.UserRegisterServiceImpl;
import com.bakefinity.model.dtos.AddressDTO;
import com.bakefinity.model.dtos.CategoryDTO;
import com.bakefinity.model.dtos.UserDTO;
import com.bakefinity.model.dtos.UserInterestsDTO;
import com.bakefinity.utils.InputValidation;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private void forwardWithData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        req.setAttribute("fname", InputValidation.validateName(req.getParameter("fname")) ? req.getParameter("fname") : "");
        req.setAttribute("lname", InputValidation.validateName(req.getParameter("lname")) ? req.getParameter("lname") : "");
        req.setAttribute("phoneNumber", InputValidation.validatePhone(req.getParameter("phoneNumber")) ? req.getParameter("phoneNumber") : "");
        req.setAttribute("birthdate", req.getParameter("birthdate"));
        req.setAttribute("job", InputValidation.validateName(req.getParameter("job")) ? req.getParameter("job") : "");
        req.setAttribute("email", UserRegisterServiceImpl.getInstance().isEmailUnique(req.getParameter("email")) ? req.getParameter("email") : "");
        req.setAttribute("username", UserRegisterServiceImpl.getInstance().isUsernameAvailable(req.getParameter("username")) ? req.getParameter("username") : "");
        req.setAttribute("password", InputValidation.validatePassword(req.getParameter("password")) ? req.getParameter("password") : "");
        req.setAttribute("creditLimit", req.getParameter("creditLimit"));
        req.setAttribute("country", req.getParameter("country"));
        req.setAttribute("city", InputValidation.validateCityStreet(req.getParameter("city")) ? req.getParameter("city") : "");
        req.setAttribute("street", InputValidation.validateCityStreet(req.getParameter("street")) ? req.getParameter("street") : "");
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
        Date birthDate = null;
        if(birthDateStr != null && !birthDateStr.trim().isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            try {
                birthDate = sdf.parse(birthDateStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
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
        try {
            if(!InputValidation.validateName(fname) || !InputValidation.validateName(lname) || !InputValidation.validatePhone(phoneNumber) || (job != null && !job.trim().isEmpty() && !InputValidation.validateName(job)) || !InputValidation.validatePassword(password) || (city != null && !city.trim().isEmpty() && !InputValidation.validateCityStreet(city)) || (street != null && !street.trim().isEmpty() && !InputValidation.validateCityStreet(street)) || !UserRegisterServiceImpl.getInstance().isUsernameAvailable(username) || !UserRegisterServiceImpl.getInstance().isEmailUnique(email)){
                req.setAttribute("error", "Oops.. Some data are not valid, please register with valid data and try again.");
                try {
                    forwardWithData(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UserDTO user = new UserDTO(fname + " " + lname, username, phoneNumber, email, password, Double.parseDouble(creditLimit), birthDate, job, LocalDateTime.now());

        int userId = 0;
        try {
            userId = UserRegisterServiceImpl.getInstance().createUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        AddressDTO address = new AddressDTO(userId, (buildingNo == null || buildingNo.trim().isEmpty()) ? -1 : Integer.parseInt(buildingNo), street, city, country);
        try {
            AddressServiceImpl.getInstance().createAddress(address);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String[] interests = req.getParameterValues("interests");
        if (interests != null) {
            for (String interest : interests) {
                try {
                    CategoryDTO category = CategoryServiceImpl.getInstance().getCategoryByName(interest);
                    UserInterestsDTO userInterests = new UserInterestsDTO(userId, category.getId());
                    UserInterestsServiceImpl.getInstance().createUserInterests(userInterests);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        resp.sendRedirect("/Ma5booz/views/user/registerConfirmation.jsp");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("views/user/register.jsp");
    }
}