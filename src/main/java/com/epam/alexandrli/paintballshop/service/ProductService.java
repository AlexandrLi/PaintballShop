package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.entity.Product;

import java.util.Comparator;

public class ProductService {
    public static final Comparator<Product> ID_ORDER = new IdComparator();
    public static final Comparator<Product> PRICE_ORDER = new PriceComparator();
    public static final Comparator<Product> AVAILABILITY_ORDER = new AvailabilityComparator();

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
