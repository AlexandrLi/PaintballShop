package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.Money;

public abstract class Product extends NamedEntity {

    private Money price;
    private int availableQuantity;
    private ProductType type;
    private String description;

    public Product() {
    }

    public Product(int id, String name, Money price, int availableQuantity, ProductType type, String description) {
        super(id, name);
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.type = type;
        this.description = description;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
