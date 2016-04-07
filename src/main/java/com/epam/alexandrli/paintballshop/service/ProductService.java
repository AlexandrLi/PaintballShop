package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.dao.jdbc.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.entity.ProductType;
import org.joda.money.Money;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductService {
    public static final Comparator<Product> ID_ORDER = new IdComparator();
    public static final Comparator<Product> PRICE_ORDER = new PriceComparator();

    private DaoFactory jdbcDaoFactory;
    private GenericDao<Product> productDao;
    private GenericDao<ProductType> productTypeDao;

    public ProductService() throws SQLException {
        initDaoFactory();
        this.productDao = jdbcDaoFactory.getDao(Product.class);
        this.productTypeDao = jdbcDaoFactory.getDao(ProductType.class);
    }

    public static Predicate<Product> isInPriceRange(Money lowPrice, Money topPrice) {
        return predicate -> predicate.getPrice().minus(lowPrice).isPositiveOrZero() && predicate.getPrice().minus(topPrice).isNegativeOrZero();
    }

    public static List<Product> filterProducts(List<Product> products, Predicate<Product> predicate) {
        return products.stream().filter(predicate).collect(Collectors.toList());
    }

    private void initDaoFactory() throws SQLException {
        if (jdbcDaoFactory == null) {
            jdbcDaoFactory = new JdbcDaoFactory();
        }
    }

    public Product getProductById(Integer id) throws SQLException {
        initDaoFactory();
        Product product = productDao.findByPK(id);
        jdbcDaoFactory.close();
        return product;
    }

    public List<Product> getFeaturedProducts() throws SQLException {
        initDaoFactory();
        List<Product> products = productDao.findAll();
        List<Product> featured = products.subList(products.size() - 9, products.size());
        jdbcDaoFactory.close();
        return featured;
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
