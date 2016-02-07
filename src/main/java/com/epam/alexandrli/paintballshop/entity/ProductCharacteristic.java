package com.epam.alexandrli.paintballshop.entity;

public class ProductCharacteristic extends NamedEntity {
    private String value;

    public ProductCharacteristic() {
    }

    public ProductCharacteristic(int id, String name, String value) {
        super(id, name);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
