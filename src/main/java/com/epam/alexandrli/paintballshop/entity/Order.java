package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Order extends BaseEntity implements Priceable {
    private User user;
    private List<OrderItem> orderItems = new ArrayList<>();
    private DateTime date;
    private String description;

    public Order() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addProduct(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public void removeProduct(OrderItem orderItem) {
        orderItems.remove(orderItem);
    }

    @Override
    public Money getPrice() {
        Money totalPrice = Money.zero(CurrencyUnit.USD);
        for (OrderItem orderItem : orderItems) {
            totalPrice = totalPrice.plus(orderItem.getPrice());
        }
        return totalPrice;
    }
}
