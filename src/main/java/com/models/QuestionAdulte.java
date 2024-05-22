package com.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class QuestionAdulte extends Question {

    public enum CategorieAdulte {
        HISTOIRE_DE_MALADIE,
        SUIVI_MEDICAL
    }

    private CategorieAdulte categorie;

    public QuestionAdulte(String enonce, CategorieAdulte categorie) {
        super(enonce);
        this.categorie = categorie;
    }

    public void setCategorie(CategorieAdulte categorie) {
        this.categorie = categorie;
    }

    public CategorieAdulte getCategorie() {
        return categorie;
    }

    public StringProperty categorieProperty() {
        return new SimpleStringProperty(categorie.name());
    }
}