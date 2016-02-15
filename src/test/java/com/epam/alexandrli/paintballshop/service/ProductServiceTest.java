package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.entity.ProductType;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.epam.alexandrli.paintballshop.service.ProductService.*;
import static org.junit.Assert.*;

public class ProductServiceTest {
    Product firstProduct;
    Product secondProduct;
    Product thirdProduct;
    List<Product> productList = new ArrayList<>();

    @Before
    public void setUp() {
        firstProduct = new Product(5, "Mask", Money.parse("USD 100"), 5, new ProductType(11, "Masks", new HashSet<>()), "Black Mask");
        secondProduct = new Product(7, "Marker", Money.parse("USD 500"), 0, new ProductType(12, "Markers", new HashSet<>()), "Electronic Marker");
        thirdProduct = new Product(3, "Tank", Money.parse("USD 50"), 2, new ProductType(13, "Tanks", new HashSet<>()), "Carbon Tank");
        productList.add(firstProduct);
        productList.add(secondProduct);
        productList.add(thirdProduct);
    }

    @Test
    public void testFilterAvailableProducts() {
        List<Product> filteredList = productList;
        assertTrue(filteredList.contains(secondProduct));
        filteredList = filterProducts(productList, isAvailable());
        assertFalse(filteredList.contains(secondProduct));

    }

    @Test
    public void testFilterProductsInPriceRange() {
        List<Product> filteredList = productList;
        assertTrue(filteredList.contains(secondProduct));
        filteredList = filterProducts(productList, isInPriceRange(Money.parse("USD 50"), Money.parse("USD 100")));
        assertFalse(filteredList.contains(secondProduct));

    }

    @Test
    public void testPriceComparator() {
        productList.sort(PRICE_ORDER);
        assertEquals(thirdProduct, productList.get(0));
        assertEquals(firstProduct, productList.get(1));
        assertEquals(secondProduct, productList.get(2));
    }

    @Test
    public void testAvailabilityComparator() {
        productList.sort(AVAILABILITY_ORDER);
        assertEquals(secondProduct, productList.get(0));
        assertEquals(thirdProduct, productList.get(1));
        assertEquals(firstProduct, productList.get(2));
    }

    @Test
    public void testIdComparator() {
        productList.sort(ID_ORDER);
        assertEquals(thirdProduct, productList.get(0));
        assertEquals(firstProduct, productList.get(1));
        assertEquals(secondProduct, productList.get(2));
    }
}