package com.epam.alexandrli.paintballshop.entity;

public class Characteristic extends NamedEntity {
    ProductType type;

    public Characteristic() {
    }

    public Characteristic(Integer id, String name, ProductType type) {
        super(id, name);
        this.type = type;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}
