package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.Money;

public class OrderItem extends BaseEntity implements Priceable {
    private Product product;
    private int amount;

    public OrderItem() {
    }

    public OrderItem(Integer id, Product product, int amount) {
        super(id);
        this.product = product;
        this.amount = amount;
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

    @Override
    public String toString() {
        return "OrderItem{" +
                "product=" + product.getName() +
                ", amount=" + amount +
                '}';
    }
}