package com.models;

import java.io.Serializable;

public class Test implements Serializable {
    
    private String nom;
    private String description;

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

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }
}
