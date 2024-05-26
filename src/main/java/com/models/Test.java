package com.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Test implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nom;
    private String description;

    private LocalDate dateTest;

    public Test(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateTest(LocalDate dateTest) {
        this.dateTest = dateTest;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateTest() {
        return dateTest;
    }
}
