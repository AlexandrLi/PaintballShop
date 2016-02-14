package com.epam.alexandrli.paintballshop.entity;

public class Characteristic extends NamedEntity {
    private String value;

    public Characteristic() {
    }

    public Characteristic(Integer id, String name, String value) {
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
