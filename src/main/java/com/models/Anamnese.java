package com.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Anamnese implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nom;
    private String description;
    private List<Question> questions;
    private String type;

    public Anamnese(String nom, String description) {
        this.nom = nom;
        this.description = description;
        this.questions = new ArrayList<>();
    }

    public Anamnese(String nom, String description, String type) {
        this.nom = nom;
        this.description = description;
        this.questions = new ArrayList<>();
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
