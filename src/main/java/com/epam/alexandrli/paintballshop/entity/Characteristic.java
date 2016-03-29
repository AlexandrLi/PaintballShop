package com.epam.alexandrli.paintballshop.entity;

public class Characteristic extends NamedEntity {
    ProductType type;

    public Characteristic() {
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}
