package com.bakefinity.controller.servlets;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Paths;

import com.bakefinity.controller.services.impls.ProductServiceImpl;
import com.bakefinity.controller.services.interfaces.ProductService;
import com.bakefinity.utils.UploadManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet(urlPatterns = "/admin/addProduct")
public class AdminAddProductController extends HttpServlet{
    ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = ProductServiceImpl.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
        req.getRequestDispatcher("/views/admin/addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String category = req.getParameter("category");
        String price = req.getParameter("price");
        String quantity = req.getParameter("stock");
        String ingredients = req.getParameter("ingredients");
        Part imagePart = req.getPart("image");

        String imageName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
        
        boolean added;
        try {
            added = productService.addNewProduct(category,name,description,price,imageName,quantity,ingredients);
        } catch (Exception e) {
            resp.sendRedirect("/views/admin/error.jsp?error-message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
            return;
        }
        if(added){ 
            // store uploaded product image 
            UploadManager uploadManager = new UploadManager(getServletContext());
            File imageFile = uploadManager.getImageFile(imageName);
            imagePart.write(imageFile.getAbsolutePath());

            System.out.println("Uploaded to: " + imageFile.getAbsolutePath());
            resp.sendRedirect(req.getContextPath() + "/admin/products");
        }
        else
            resp.sendRedirect(req.getContextPath() + "/views/admin/error.jsp?error-message=Could not add new product.Try again.");
    };
}
