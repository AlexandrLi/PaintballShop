package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.Money;

public class OrderItem extends BaseEntity implements Priceable {
    private Product product;
    private int amount;
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Product product, int amount, Order order) {
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public Money getPrice() {
        return product.getPrice().multipliedBy(amount);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "product=" + product.getName() +
                ", amount=" + amount + '}';
    }
}
