package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.Money;

public class OrderLine extends BaseEntity {
    private Product product;
    private int amount;
    private Money price;
    private Order order;

    public OrderLine() {
    }

    public OrderLine(int id, Product product, int amount, Money price, Order order) {
        super(id);
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
