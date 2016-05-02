package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.validator.Validator;
import com.epam.alexandrli.paintballshop.entity.Image;
import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.entity.ProductType;
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

import static com.epam.alexandrli.paintballshop.validator.Validator.MONEY;

public class AddProductAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(AddProductAction.class);
    private Validator validator = new Validator();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String type = req.getParameter("typeId");
        String descriptionRu = req.getParameter("descriptionRu");
        String descriptionEn = req.getParameter("descriptionEn");
        ProductService productService = new ProductService();
        try {
            Product product = new Product();
            product.setName(name);
            product.setType(new ProductType(Integer.valueOf(type)));
            product.setDescriptionRu(descriptionRu);
            product.setDescriptionEn(descriptionEn);
            if (!validator.validate(price, MONEY)) {
                req.setAttribute("moneyError", "true");
                req.setAttribute("product", product);
                logger.info("Invalid money format - {}", price);
                return new ActionResult("add-product");
            }
            product.setPrice(Money.parse("KZT " + price));
            Part imagePart = req.getPart("image");
            if (!imagePart.getContentType().startsWith("image")) {
                req.setAttribute("imageError", "true");
                req.setAttribute("product", product);
                logger.info("Invalid content type - {}", imagePart.getContentType());
                return new ActionResult("add-product");
            }
            Image image = new Image();
            image.setName(name.replaceAll("\\s", "").toLowerCase());
            image.setModified(DateTime.now());
            image.setContentType(imagePart.getContentType());
            image.setContent(imagePart.getInputStream());
            Product newProduct = productService.addProduct(product, image);
            productService.addProductOnStorage(newProduct);
            logger.info("{} inserted in db and added on central storage by {}", newProduct, req.getSession(false).getAttribute("loggedUser"));
        } catch (ServiceException | IOException | ServletException e) {
            throw new ActionException("Could not add product", e);
        }
        return new ActionResult("manage/products", true);
    }

}
