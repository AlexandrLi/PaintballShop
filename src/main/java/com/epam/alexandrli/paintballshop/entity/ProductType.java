package com.epam.alexandrli.paintballshop.entity;

import java.util.HashSet;
import java.util.Set;

public class ProductType extends NamedEntity {

    private Set<ProductCharacteristic> characteristics = new HashSet<>();

    public ProductType() {
    }

    public ProductType(int id, String name, Set<ProductCharacteristic> characteristics) {
        super(id, name);
        this.characteristics = characteristics;
    }
}
