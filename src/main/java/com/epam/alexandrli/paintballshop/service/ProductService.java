package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.entity.Product;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductService {
    public static final Comparator<Product> ID_ORDER = new IdComparator();
    public static final Comparator<Product> PRICE_ORDER = new PriceComparator();
    public static final Comparator<Product> AVAILABILITY_ORDER = new AvailabilityComparator();

    public static Predicate<Product> isAvailable() {
        return predicate -> predicate.getAvailableQuantity() > 0;
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

    private static class AvailabilityComparator implements Comparator<Product> {
        @Override
        public int compare(Product firstProduct, Product secondProduct) {

            return Integer.compare(firstProduct.getAvailableQuantity(), secondProduct.getAvailableQuantity());
        }
    }

    private static class IdComparator implements Comparator<Product> {
        @Override
        public int compare(Product firstProduct, Product secondProduct) {
            return firstProduct.getId().compareTo(secondProduct.getId());
        }
    }
}
