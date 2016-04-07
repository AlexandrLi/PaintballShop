package com.epam.alexandrli.paintballshop.entity;

import java.util.HashSet;
import java.util.Set;

public class ProductType extends NamedEnRuEntity {

    private Set<Characteristic> characteristics = new HashSet<>();

    public ProductType() {
    }

    public Set<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Set<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }
}
