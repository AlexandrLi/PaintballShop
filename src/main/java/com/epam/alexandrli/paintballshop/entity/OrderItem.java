package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.Money;

public class OrderItem extends BaseEntity implements Priceable {
    private Product product;
    private Order order;
    private int amount;

    public OrderItem() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
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
    public String toString() {
        return "OrderItem{" + super.toString() +
                "productId=" + product.getId() +
                ", amount=" + amount +
                '}';
    }

    @Override
    public Money getPrice() {
        return product.getPrice().multipliedBy(amount);
    }

}
