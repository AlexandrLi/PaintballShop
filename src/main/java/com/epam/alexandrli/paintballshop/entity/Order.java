package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Order extends BaseEntity implements Priceable {
    private UserProfile userProfile;
    private List<OrderLine> orderLines = new ArrayList<>();
    private DateTime date;

    public Order() {
    }

    public Order(Integer id, UserProfile userProfile, List<OrderLine> orderLines, DateTime date) {
        super(id);
        this.userProfile = userProfile;
        this.orderLines = orderLines;
        this.date = date;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public void addProduct(OrderLine orderLine) {
        orderLines.add(orderLine);
    }

    public void removeProduct(OrderLine orderLine) {
        orderLines.remove(orderLine);
    }

    @Override
    public Money getPrice() {
        Money totalPrice = Money.zero(CurrencyUnit.USD);
        for (OrderLine orderLine : orderLines) {
            totalPrice.plus(orderLine.getPrice());
        }
        return totalPrice;
    }
}
