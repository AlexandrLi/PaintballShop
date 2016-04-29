package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.Validator;
import com.epam.alexandrli.paintballshop.entity.Image;
import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.service.ProductService;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

import static com.epam.alexandrli.paintballshop.Validator.MONEY;

public class EditProductAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(EditProductAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String price = req.getParameter("price");
        Validator validator = new Validator();
        if (!validator.validate(price, MONEY)) {
            req.setAttribute("flash.moneyError", "true");
            logger.info("Invalid money format - {}", price);
            return new ActionResult(req.getHeader("referer"), true);
        }
        try {
            Part imagePart = req.getPart("image");
            if (!imagePart.getContentType().startsWith("image")) {
                req.setAttribute("flash.imageError", "true");
                logger.info("Invalid content type - {}", imagePart.getContentType());
                return new ActionResult(req.getHeader("referer"), true);
            }
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String type = req.getParameter("typeId");
            String descriptionRu = req.getParameter("descriptionRu");
            String descriptionEn = req.getParameter("descriptionEn");
            ProductService productService = new ProductService();
            Product product = productService.getProductById(id);
            product.setName(name);
            product.getType().setId(Integer.valueOf(type));
            product.setPrice(Money.parse("KZT " + price));
            product.setDescriptionRu(descriptionRu);
            product.setDescriptionEn(descriptionEn);
            productService.updateProduct(product);
            if (imagePart.getSize() != 0) {
                Image image = productService.getProductPreviewImage(id);
                image.setName(name.replaceAll("\\s", "").toLowerCase());
                image.setContentType(imagePart.getContentType());
                image.setModified(DateTime.now());
                image.setContent(imagePart.getInputStream());
                productService.updateProductImage(image);
            }
            logger.info("{} updated by {}", product, req.getSession(false).getAttribute("loggedUser"));
        } catch (ServiceException | IOException | ServletException e) {
            throw new ActionException("Could not edit product", e);
        }
        return new ActionResult("manage/products", true);
    }
}
