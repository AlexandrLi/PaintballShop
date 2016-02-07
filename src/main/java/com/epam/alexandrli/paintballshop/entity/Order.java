package com.epam.alexandrli.paintballshop.entity;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Order extends BaseEntity {
    private UserProfile userProfile;
    private List<OrderLine> orderLines = new ArrayList<>();
    private DateTime dateTime;

    public Order() {
    }

    public Order(int id, UserProfile userProfile, List<OrderLine> orderLines, DateTime dateTime) {
        super(id);
        this.userProfile = userProfile;
        this.orderLines = orderLines;
        this.dateTime = dateTime;
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

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
}
