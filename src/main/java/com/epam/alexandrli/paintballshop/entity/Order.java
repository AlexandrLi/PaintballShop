package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class Order extends BaseEntity implements Priceable {
    private User user;
    private List<OrderItem> orderItems = new ArrayList<>();
    private DateTime created;
    private String description;
    private OrderStatus status;

    public Order() {
        created = DateTime.now();
    }

    public Order(Integer id) {
        setId(id);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public String getFormattedCreatedTime() {
        DateTimeFormatter formatter = DateTimeFormat.shortDateTime();
        return created.toString(formatter);
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

    @Override
    public String toString() {
        return "Order{" + super.toString() +
                "created=" + DateTimeFormat.forPattern("dd-MM-yyyy hh:mm:ss").print(created) +
                ", userId=" + user.getId() +
                ", description='" + description + '\'' +
                ", statusId=" + status.getId() +
                '}';
    }

    @Override
    public Money getPrice() {
        Money totalPrice = Money.zero(CurrencyUnit.getInstance("KZT"));
        for (OrderItem orderItem : orderItems) {
            totalPrice = totalPrice.plus(orderItem.getPrice());
        }
        return totalPrice;
    }
}
