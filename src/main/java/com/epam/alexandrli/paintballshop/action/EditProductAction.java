package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Image;
import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.service.ProductService;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import org.joda.money.Money;
import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

public class EditProductAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        String price = req.getParameter("price");
        String descriptionRu = req.getParameter("descriptionRu");
        String descriptionEn = req.getParameter("descriptionEn");
        ProductService productService = new ProductService();
        try {
            Product product = productService.getProductById(id);
            product.setName(name);
            product.getType().setId(Integer.valueOf(type));
            product.setPrice(Money.parse("KZT " + price));
            product.setDescriptionRu(descriptionRu);
            product.setDescriptionEn(descriptionEn);
            productService.updateProduct(product);
            Part imagePart = req.getPart("image");
            if (imagePart != null) {
                Image image = productService.getProductPreviewImage(id);
                image.setName(name.trim());
                image.setContentType(imagePart.getContentType());
                image.setModified(DateTime.now());
                image.setContent(imagePart.getInputStream());
                productService.updateProductImage(image);
            }
        } catch (ServiceException | IOException | ServletException e) {
            throw new ActionException("Could not edit product", e);
        }
        return new ActionResult("products");
    }
}
