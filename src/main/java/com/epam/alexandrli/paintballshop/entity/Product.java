package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.Money;

import java.util.HashMap;
import java.util.Map;

public class Product extends NamedEntity {

    private Money price;
    private ProductType type;
    private String description;
    private Map<Characteristic, CharacteristicValue> characteristics = new HashMap<>();

    public Product() {
    }

    public Product(Integer id, String name, Money price, int availableQuantity, ProductType type, String description) {
        super(id, name);
        this.price = price;
        this.type = type;
        this.description = description;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", type=" + type + '}';
    }
}
