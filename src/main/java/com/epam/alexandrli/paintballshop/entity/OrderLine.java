package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.Money;

public class OrderLine extends BaseEntity implements Priceable {
    private Product product;
    private int amount;

    public OrderLine() {
    }

    public OrderLine(Integer id, Product product, int amount) {
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

}
