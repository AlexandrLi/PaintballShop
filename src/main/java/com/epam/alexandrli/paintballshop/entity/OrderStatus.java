package com.epam.alexandrli.paintballshop.entity;

public class OrderStatus extends NamedEntity {

    public OrderStatus() {
    }

    public OrderStatus(Integer id) {
        setId(id);
    }

    @Override
    public String toString() {
        return "OrderStatus{" + super.toString() + "}";
    }
}
