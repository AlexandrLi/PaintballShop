package com.epam.alexandrli.paintballshop.entity;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Order extends BaseEntity {
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

    public boolean addProduct(OrderLine orderLine) {
        return orderLines.add(orderLine);
    }

    public void removeProduct(OrderLine orderLine) {
        orderLines.remove(orderLine);
    }
}
