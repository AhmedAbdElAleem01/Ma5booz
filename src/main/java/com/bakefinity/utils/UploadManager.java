package com.bakefinity.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.ServletContext;

public class UploadManager {
    private final ServletContext context;

    public UploadManager(ServletContext context) {
        this.context = context;
    }

    public File getUploadDir() {
        String relativePath = context.getInitParameter("upload.location"); 
        String rootPath = System.getProperty("user.dir");
        File uploadDir = new File(rootPath, relativePath);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); 
        }
        
        return uploadDir;
    }

    public File getImageFile(String fileName) {
        String staticFolderPath = context.getRealPath("/static/img"); //preloaded images 
        String uploadedFolderPath = getUploadDir().getAbsolutePath();

        File staticImage = new File(staticFolderPath, fileName);
        if(staticImage.exists()){
            return new File(staticFolderPath, fileName);
        }
        File uploadedImage = new File(uploadedFolderPath, fileName);
        if(uploadedImage.exists()){
            return new File(uploadedFolderPath, fileName);
        }
        System.out.println("Image not found");
        return new File(getUploadDir(), fileName);
    }
}
