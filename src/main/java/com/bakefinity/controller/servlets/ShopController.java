package com.bakefinity.controller.servlets;

import java.io.IOException;
import java.util.List;

import com.bakefinity.controller.services.impls.CategoryServiceImpl;
import com.bakefinity.controller.services.impls.ProductServiceImpl;
import com.bakefinity.controller.services.interfaces.CategoryService;
import com.bakefinity.controller.services.interfaces.ProductService;
import com.bakefinity.model.dtos.CategoryDTO;
import com.bakefinity.model.dtos.ProductDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/shop")
public class ShopController extends HttpServlet {

    private CategoryService categoryService;
    private ProductService productService;
    private Gson gson;
    private final int pageSize = 1;

    @Override
    public void init() throws ServletException {
        categoryService = CategoryServiceImpl.getInstance();
        productService = ProductServiceImpl.getInstance();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if ("XMLHttpRequest".equalsIgnoreCase(req.getHeader("X-Requested-With"))) {
            handleAjaxRequest(req, resp);
        } else {
            handlePageRequest(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        if ("XMLHttpRequest".equalsIgnoreCase(req.getHeader("X-Requested-With"))) {
            if (req.getParameter("page") != null) {
                handleAjaxPagination(req, resp);
            } else {
                handleAjaxSearch(req, resp);
            }
        } else {
            doGet(req, resp);
        }
    }

    // Non-AJAX Page Rendering
    private void handlePageRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve all categories for sidebar
        List<CategoryDTO> categories = categoryService.getAllCategories();
        req.setAttribute("categories", categories);

        // Check for category filter
        String catIdStr = req.getParameter("catID");
        List<ProductDTO> products;
        if (catIdStr != null && !catIdStr.trim().isEmpty()) {
            int catID = Integer.parseInt(catIdStr);
            products = productService.getProductsByCategory(catID);
        } else {
            products = productService.getAllProducts();
        }

        // Calculate total pages (if you plan to paginate on non-AJAX requests)
        int totalPages = (int) Math.ceil((double) products.size() / pageSize);
        req.setAttribute("products", products);
        req.setAttribute("currentPage", 1);
        req.setAttribute("totalPages", totalPages);

        // Forward to JSP view for rendering the shop page
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/shop.jsp");
        dispatcher.forward(req, resp);
    }

    // AJAX: Handle initial load for category filtering or product listing
    private void handleAjaxRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catIdStr = req.getParameter("catID");
        int page = 1; 
        List<ProductDTO> products;
        int totalProducts;

        try {
            if (catIdStr != null && !catIdStr.trim().isEmpty()){
                int catID = Integer.parseInt(catIdStr);
                if(catID == 0){products = productService.getAllProducts(); totalProducts = productService.getTotalProductCount();} 
                else{
                    products = productService.getProductsByCategoryPage(catID, page, pageSize);
                    totalProducts = productService.getTotalProductsByCategory(catID);
                }
            } else {
                products = productService.getProductsByPage(page, pageSize);
                totalProducts = productService.getTotalProductCount();
            }

            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("currentPage", page);
            jsonResponse.addProperty("totalPages", totalPages);
            JsonArray jsonProducts = gson.toJsonTree(products).getAsJsonArray();
            jsonResponse.add("products", jsonProducts);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(jsonResponse));
        } catch (Exception e) {
            throw new ServletException("Error handling AJAX request", e);
        }
    }

    // AJAX: Handle Pagination Requests
    private void handleAjaxPagination(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int catID = 0;

        try {
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
            if (req.getParameter("catID") != null && !req.getParameter("catID").trim().isEmpty()) {
                catID = Integer.parseInt(req.getParameter("catID"));
            }

            List<ProductDTO> products;
            int totalProducts;
            if (catID > 0) {
                products = productService.getProductsByCategoryPage(catID, page, pageSize);
                totalProducts = productService.getTotalProductsByCategory(catID);
            } else {
                products = productService.getProductsByPage(page, pageSize);
                totalProducts = productService.getTotalProductCount();
            }

            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("currentPage", page);
            jsonResponse.addProperty("totalPages", totalPages);
            JsonArray jsonProducts = gson.toJsonTree(products).getAsJsonArray();
            jsonResponse.add("products", jsonProducts);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(jsonResponse));
        } catch (Exception e) {
            throw new ServletException("Error handling AJAX pagination", e);
        }
    }

    // AJAX: Handle Search Requests (by product name)
    private void handleAjaxSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("q");

        try {
            List<ProductDTO> products = productService.searchProductsByName(query);

            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("currentPage", 1);
            jsonResponse.addProperty("totalPages", products.size() / pageSize);
            JsonArray jsonProducts = gson.toJsonTree(products).getAsJsonArray();
            jsonResponse.add("products", jsonProducts);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(jsonResponse));
        } catch (Exception e) {
            throw new ServletException("Error handling AJAX search", e);
        }
    }
}
