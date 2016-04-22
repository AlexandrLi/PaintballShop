package com.epam.alexandrli.paintballshop.entity;

import java.util.Locale;

public class NamedEntity extends BaseEntity {
    private String nameRu;
    private String nameEn;

    public NamedEntity() {
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getName(Locale locale) {
        if (locale != null && locale.getLanguage().equals("ru")) {
            return nameRu;
        }
        return nameEn;
    }
}
