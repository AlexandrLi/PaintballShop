package com.epam.alexandrli.paintballshop.entity;

public class Characteristic extends NamedEnRuEntity {
    private ProductType type;

    public Characteristic() {
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

}
