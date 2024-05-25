package com.models;

import java.io.Serializable;

public class Proposition implements Serializable {
    private String texte;
    private static final long serialVersionUID = 1L;

    public Proposition(String texte) {
        this.texte = texte;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
