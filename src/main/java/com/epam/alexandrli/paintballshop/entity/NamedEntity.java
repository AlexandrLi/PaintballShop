package com.epam.alexandrli.paintballshop.entity;

public abstract class NamedEntity extends BaseEntity {
    private String name;

    public NamedEntity() {
    }

    public NamedEntity(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
