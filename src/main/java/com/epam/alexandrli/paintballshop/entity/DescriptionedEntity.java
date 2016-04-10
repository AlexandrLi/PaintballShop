package com.epam.alexandrli.paintballshop.entity;

import java.util.Locale;

public class DescriptionedEntity extends BaseEntity {
    private String descriptionRu;
    private String descriptionEn;

    public DescriptionedEntity() {
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescription(Locale locale) {
        if (locale.getLanguage().equals("ru")) {
            return descriptionRu;
        }
        return descriptionEn;
    }
}
