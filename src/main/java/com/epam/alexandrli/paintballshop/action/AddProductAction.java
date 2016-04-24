package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Image;
import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.entity.ProductType;
import com.epam.alexandrli.paintballshop.service.ProductService;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import org.joda.money.Money;
import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

public class AddProductAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        String price = req.getParameter("price");
        String descriptionRu = req.getParameter("descriptionRu");
        String descriptionEn = req.getParameter("descriptionEn");
        ProductService productService = new ProductService();
        try {
            Product product = new Product();
            product.setName(name);
            product.setType(new ProductType(Integer.valueOf(type)));
            product.setPrice(Money.parse("KZT " + price));
            product.setDescriptionRu(descriptionRu);
            product.setDescriptionEn(descriptionEn);
            Part imagePart = req.getPart("image");
            Image image = new Image();
            image.setName(name.replaceAll("\\s", "").toLowerCase());
            image.setModified(DateTime.now());
            image.setContentType(imagePart.getContentType());
            image.setContent(imagePart.getInputStream());
            Product newProduct = productService.addProduct(product, image);
            productService.addProductOnStorage(newProduct);
        } catch (ServiceException | IOException | ServletException e) {
            throw new ActionException("Could not add product", e);
        }
        return new ActionResult("manage/products", true);
    }
}
