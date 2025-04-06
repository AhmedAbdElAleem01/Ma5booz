package com.bakefinity.controller.servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.bakefinity.utils.UploadManager;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/product-image/*" , description = "serves products images from external folder into jsp")
public class ProductImageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String filename = req.getPathInfo().substring(1); 
        UploadManager uploadManager = new UploadManager(getServletContext());
        File imageFile = uploadManager.getImageFile(filename);

        if (!imageFile.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        resp.setContentType(getServletContext().getMimeType(filename));
        resp.setContentLengthLong(imageFile.length());
        Files.copy(imageFile.toPath(), resp.getOutputStream());
    }
}

