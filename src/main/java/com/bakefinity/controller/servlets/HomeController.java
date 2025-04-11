package com.bakefinity.controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bakefinity.controller.services.impls.CategoryServiceImpl;
import com.bakefinity.controller.services.impls.ProductServiceImpl;
import com.bakefinity.controller.services.impls.UserInterestsServiceImpl;
import com.bakefinity.controller.services.interfaces.CategoryService;
import com.bakefinity.controller.services.interfaces.ProductService;
import com.bakefinity.controller.services.interfaces.UserInterestsService;
import com.bakefinity.model.dtos.CategoryDTO;
import com.bakefinity.model.dtos.ProductDTO;
import com.bakefinity.model.dtos.UserDTO;
import com.bakefinity.model.dtos.UserInterestsDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/home")
public class HomeController extends HttpServlet {
    private ProductService productService;
    private CategoryService categoryService;
    private UserInterestsService userInterestsService;

    @Override
    public void init() throws ServletException {
        productService = ProductServiceImpl.getInstance();
        categoryService = CategoryServiceImpl.getInstance();
        userInterestsService = UserInterestsServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDTO> classicProducts = productService.getClassicProducts(8);

        List<CategoryDTO> homeCategories = categoryService.getAllCategories();

        if(req.getSession().getAttribute("user") != null){
           UserDTO user = (UserDTO) req.getSession().getAttribute("user");
           System.out.println(userInterestsService.getUserInterests(user.getId()));
           List<UserInterestsDTO> interests = userInterestsService.getUserInterests(user.getId());
           List<CategoryDTO>cats = new ArrayList<>();
           for (UserInterestsDTO interest : interests) {
                cats.add(new CategoryDTO(categoryService.getCategoryById(interest.getCategoryId())));
           }
           System.out.println(cats);
           req.setAttribute("prefs", cats);
        }

        req.setAttribute("classicProducts", classicProducts);
        req.setAttribute("homeCategories", homeCategories);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/home.jsp");
        dispatcher.forward(req, resp);
    }
}
