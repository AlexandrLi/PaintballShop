package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.entity.Product;
import org.joda.money.Money;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductService {
    public static final Comparator<Product> ID_ORDER = new IdComparator();
    public static final Comparator<Product> PRICE_ORDER = new PriceComparator();

    public static Predicate<Product> isInPriceRange(Money lowPrice, Money topPrice) {
        return predicate -> predicate.getPrice().minus(lowPrice).isPositiveOrZero() && predicate.getPrice().minus(topPrice).isNegativeOrZero();
    }

    public static List<Product> filterProducts(List<Product> products, Predicate<Product> predicate) {
        return products.stream().filter(predicate).collect(Collectors.<Product>toList());
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
