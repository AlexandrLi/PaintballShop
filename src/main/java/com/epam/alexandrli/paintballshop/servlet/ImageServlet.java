package com.epam.alexandrli.paintballshop.servlet;

import com.epam.alexandrli.paintballshop.entity.Image;
import com.epam.alexandrli.paintballshop.service.ProductService;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "ImageServlet", urlPatterns = "/img/*")
public class ImageServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(ImageServlet.class);
    private static final int DEFAULT_SIZE = 1024;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        final byte[] buffer = new byte[DEFAULT_SIZE];
        int length;
        try (ServletOutputStream os = resp.getOutputStream()) {
            ProductService productService = new ProductService();
            String productId = pathInfo.substring(1);
            Image productImage = productService.getProductPreviewImage(productId);
            resp.setContentType(productImage.getContentType());
            InputStream content = productImage.getContent();
            while ((length = content.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }
            logger.debug("Product (id = {}) image loaded. {}", productId, productImage);
        } catch (ServiceException e) {
            throw new ServletException("Couldn't load product image", e);
        }
    }
}
