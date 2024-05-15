package com.models;

public class QuestionEnfant extends Question {

    public enum CategorieEnfant {
        STRUCTURE_FAMILIALE,
        DYNAMIQUE_FAMILIALE,
        ANTECEDENTS_FAMILIAUX,
        CONDITIONS_NATALES,
        DEVELOPPEMENT_PSYCHOMOTEUR,
        DEVELOPPEMENT_LANGAGIER,
        CARACTERE_ET_COMPORTEMENT
    }

    private CategorieEnfant categorie;

    public QuestionEnfant(String enonce, CategorieEnfant categorie) {
        super(enonce);
        this.categorie = categorie;
    }

    public void setCategorie(CategorieEnfant categorie) {
        this.categorie = categorie;
    }

    public CategorieEnfant getCategorie() {
        return categorie;
    }
}
