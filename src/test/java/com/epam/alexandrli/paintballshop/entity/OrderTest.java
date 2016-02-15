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
    OrderLine firstLine;
    OrderLine secondLine;
    OrderLine thirdLine;
    List<OrderLine> orderLines = new ArrayList<>();
    Order order;

    @Before
    public void setUp() {
        Product firstProduct = new Product(5, "Mask", Money.parse("USD 100"), 5, new ProductType(11, "Masks", new HashSet<>()), "Black Mask");
        Product secondProduct = new Product(7, "Marker", Money.parse("USD 500"), 0, new ProductType(12, "Markers", new HashSet<>()), "Electronic Marker");
        Product thirdProduct = new Product(3, "Tank", Money.parse("USD 50"), 2, new ProductType(13, "Tanks", new HashSet<>()), "Carbon Tank");
        firstLine = new OrderLine(11, firstProduct, 2);
        secondLine = new OrderLine(13, secondProduct, 1);
        thirdLine = new OrderLine(12, thirdProduct, 5);
        orderLines.add(firstLine);
        orderLines.add(secondLine);
        orderLines.add(thirdLine);
        order = new Order(333, new UserProfile(), orderLines, new DateTime());
    }

    @Test
    public void testGetPrice() {
        Money totalPrice = Money.zero(CurrencyUnit.USD);
        totalPrice = totalPrice.plus(order.getOrderLines().get(0).getPrice());
        totalPrice = totalPrice.plus(order.getOrderLines().get(1).getPrice());
        totalPrice = totalPrice.plus(order.getOrderLines().get(2).getPrice());
        assertEquals(totalPrice, order.getPrice());
    }
}