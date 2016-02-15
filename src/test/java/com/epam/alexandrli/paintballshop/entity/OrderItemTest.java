package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static junit.framework.Assert.assertEquals;

public class OrderItemTest {
    OrderItem firstLine;
    OrderItem secondLine;

    @Before
    public void setUp() {
        Product firstProduct = new Product(5, "Mask", Money.parse("USD 100"), 5, new ProductType(11, "Masks", new HashSet<>()), "Black Mask");
        Product secondProduct = new Product(7, "Marker", Money.parse("USD 500"), 0, new ProductType(12, "Markers", new HashSet<>()), "Electronic Marker");

        firstLine = new OrderItem(11, firstProduct, 2);
        secondLine = new OrderItem(13, secondProduct, 1);
    }

    @Test
    public void testGetPrice() {
        Money firstLinePrice = firstLine.getProduct().getPrice().multipliedBy(firstLine.getAmount());
        Money secondLinePrice = secondLine.getProduct().getPrice().multipliedBy(secondLine.getAmount());
        assertEquals(firstLinePrice, firstLine.getPrice());
        assertEquals(secondLinePrice, secondLine.getPrice());
    }
}