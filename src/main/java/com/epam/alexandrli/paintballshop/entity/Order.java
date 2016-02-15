package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Order extends BaseEntity implements Priceable {
    private UserProfile userProfile;
    private List<OrderItem> orderItems = new ArrayList<>();
    private DateTime date;

    public Order() {
    }

    public Order(Integer id, UserProfile userProfile, List<OrderItem> orderItems, DateTime date) {
        super(id);
        this.userProfile = userProfile;
        this.orderItems = orderItems;
        this.date = date;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
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
