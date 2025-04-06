package com.bakefinity.controller.servlets;
import java.io.IOException;
import java.net.URLEncoder;
import com.bakefinity.controller.services.impls.ProductServiceImpl;
import com.bakefinity.controller.services.interfaces.ProductService;
import com.bakefinity.model.dtos.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/editProduct")
public class AdminEditProductController extends HttpServlet{
    ProductService productService;
    ProductDTO product;

    @Override
    public void init() throws ServletException {
        productService = ProductServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
        int productId = Integer.parseInt(req.getParameter("id"));

        product = productService.getProductById(productId);
        req.setAttribute("product", product);
        req.getRequestDispatcher("/views/admin/editProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String ingredients = req.getParameter("ingredients");
        String category = req.getParameter("category");
        String price = req.getParameter("price");
        String quantity = req.getParameter("stock");
       
        boolean updated;
        try {
            updated = productService.updateProduct(product ,category, name, description, price,product.getImageUrl(), quantity, ingredients);
        } catch (Exception e) {
            resp.sendRedirect("/views/admin/error.jsp?error-message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
            return;
        }
        if(updated)
            resp.sendRedirect(req.getContextPath() + "/admin/products");
        else
            resp.sendRedirect(req.getContextPath() + "/views/admin/error.jsp?error-message=Could not update the product.Try again.");    
    }
}
