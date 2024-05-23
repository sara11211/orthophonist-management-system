package com.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nom;
    private String capacite;
    private ArrayList<Question> questions;

    public Test(String nom, String capacite, ArrayList<Question> questions) {
        this.nom = nom;
        this.capacite = capacite;
        this.questions = questions;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getNom() {
        return nom;
    }

    public String getCapacite() {
        return capacite;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
