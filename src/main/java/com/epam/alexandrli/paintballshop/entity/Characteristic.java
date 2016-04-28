package com.epam.alexandrli.paintballshop.entity;

public class Characteristic extends NamedEntity {
    private ProductType type;

    public Characteristic() {
    }

    public Characteristic(Integer id) {
        setId(id);
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Characteristic{" + super.toString() +
                "typeId=" + type.getId() +
                '}';
    }
}
