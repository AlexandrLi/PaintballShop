package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.Money;

public class OrderLine extends BaseEntity implements Priceable {
    private Product product;
    private int amount;
    private Order order;

    public OrderLine() {
    }

    public OrderLine(Integer id, Product product, int amount, Order order) {
        super(id);
        this.product = product;
        this.amount = amount;
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

    @Override
    public Money getPrice() {
        return product.getPrice().multipliedBy(amount);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
