package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.dao.jdbc.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.CharacteristicItem;
import com.epam.alexandrli.paintballshop.entity.Image;
import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.entity.ProductType;
import org.joda.money.Money;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductService {
    public static final Comparator<Product> ID_ORDER = new IdComparator();
    public static final Comparator<Product> PRICE_ORDER = new PriceComparator();

    private DaoFactory jdbcDaoFactory;
    private GenericDao<Product> productDao;
    private ProductTypeService productTypeService;
    private GenericDao<Image> imageDao;
    private CharacteristicItemService characteristicItemService;

    public ProductService() throws SQLException {
        jdbcDaoFactory = new JdbcDaoFactory();
        this.productDao = jdbcDaoFactory.getDao(Product.class);
        this.productTypeService = new ProductTypeService();
        this.imageDao = jdbcDaoFactory.getDao(Image.class);
        this.characteristicItemService = new CharacteristicItemService();
        jdbcDaoFactory.close();
    }

    public static Predicate<Product> isInPriceRange(Money lowPrice, Money topPrice) {
        return predicate -> predicate.getPrice().minus(lowPrice).isPositiveOrZero() && predicate.getPrice().minus(topPrice).isNegativeOrZero();
    }

    public static List<Product> filterProducts(List<Product> products, Predicate<Product> predicate) {
        return products.stream().filter(predicate).collect(Collectors.toList());
    }

    public Product getProductById(String id) throws SQLException {
        jdbcDaoFactory.getConnection();
        Product product = productDao.findByPK(Integer.parseInt(id));
        jdbcDaoFactory.close();
        return product;
    }

    public List<Product> getFeaturedProducts() throws SQLException {
        jdbcDaoFactory.getConnection();
        List<Product> products = productDao.findAll();
        List<Product> featured = products.subList(products.size() - 9, products.size());
        jdbcDaoFactory.close();
        return featured;
    }

    public Image getProductPreviewImage(String id) throws SQLException {
        jdbcDaoFactory.getConnection();
        List<Image> images = imageDao.findAllByParams(Collections.singletonMap("product_id", id));
        jdbcDaoFactory.close();
        return images.get(0);
    }

    public List<Product> getAllProductsByType(String typeId) throws SQLException {
        jdbcDaoFactory.getConnection();
        List<Product> products = productDao.findAllByParams(Collections.singletonMap("product_type_id", typeId));
        jdbcDaoFactory.close();
        return products;
    }

    public Product getAllProduct(String id) throws SQLException {
        jdbcDaoFactory.getConnection();
        Product product = productDao.findByPK(Integer.parseInt(id));
        ProductType productType = productTypeService.getProductTypeById(productDao.findColumnByPK("product_type_id", Integer.parseInt(id)));
        product.setType(productType);
        Map<String, String> productIdParam = Collections.singletonMap("product_id", id);
        List<Image> images = imageDao.findAllByParams(productIdParam);
        product.setImages(images);
        List<CharacteristicItem> characteristics = characteristicItemService.getAllItemsByParams(productIdParam);
        product.setCharacteristics(characteristics);
        jdbcDaoFactory.close();
        return product;

    }

    private static class PriceComparator implements Comparator<Product> {
        @Override
        public int compare(Product firstProduct, Product secondProduct) {
            return firstProduct.getPrice().compareTo(secondProduct.getPrice());
        }
    }

    private static class IdComparator implements Comparator<Product> {
        @Override
        public int compare(Product firstProduct, Product secondProduct) {
            return firstProduct.getId().compareTo(secondProduct.getId());
        }
    }
}
