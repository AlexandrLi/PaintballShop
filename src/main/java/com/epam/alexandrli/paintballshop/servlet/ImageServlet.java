package com.epam.alexandrli.paintballshop.servlet;

import com.epam.alexandrli.paintballshop.entity.Image;
import com.epam.alexandrli.paintballshop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@WebServlet(name = "ImageServlet", urlPatterns = "/img/*")
public class ImageServlet extends HttpServlet {
    private static final int DEFAULT_SIZE = 1024;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getPathInfo();
        final byte[] buffer = new byte[DEFAULT_SIZE];
        int lenght;
        try (ServletOutputStream os = resp.getOutputStream()) {
            ProductService productService = new ProductService();
            Image productImage = productService.getProductPreviewImage(productId.substring(1));
            resp.setContentType(productImage.getContentType());
            InputStream content = productImage.getContent();
            while ((lenght = content.read(buffer)) != -1) {
                os.write(buffer, 0, lenght);
            }
        } catch (SQLException e) {
            throw new ImageServletException("Couldn't load product image", e);
        }
    }
}
