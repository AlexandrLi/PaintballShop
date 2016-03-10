package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderTest {
    OrderItem firstLine;
    OrderItem secondLine;
    OrderItem thirdLine;
    List<OrderItem> orderItems = new ArrayList<>();
    Order order;

    @Before
    public void setUp() {
        Product firstProduct = new Product(5, "Mask", Money.parse("USD 100"), new ProductType(11, "Masks", new HashSet<>()), "Black Mask");
        Product secondProduct = new Product(7, "Marker", Money.parse("USD 500"), new ProductType(12, "Markers", new HashSet<>()), "Electronic Marker");
        Product thirdProduct = new Product(3, "Tank", Money.parse("USD 50"), new ProductType(13, "Tanks", new HashSet<>()), "Carbon Tank");
        firstLine = new OrderItem(firstProduct, 11, new Order());
        secondLine = new OrderItem(secondProduct, 11, new Order());
        thirdLine = new OrderItem(thirdProduct, 11, new Order());
        orderItems.add(firstLine);
        orderItems.add(secondLine);
        orderItems.add(thirdLine);
        order = new Order(333, new UserProfile(), orderItems, new DateTime(), "Test");
    }

    @Test
    public void testGetPrice() {
        Money totalPrice = Money.zero(CurrencyUnit.USD);
        totalPrice = totalPrice.plus(order.getOrderItems().get(0).getPrice());
        totalPrice = totalPrice.plus(order.getOrderItems().get(1).getPrice());
        totalPrice = totalPrice.plus(order.getOrderItems().get(2).getPrice());
        assertEquals(totalPrice, order.getPrice());
    }
}