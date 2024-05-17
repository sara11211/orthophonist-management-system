package com.models;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;


public class Anamnese implements Serializable{

    private String nom;
    private String description;
    private List<Question> questions; 

    public Anamnese(String nom, String description) {
        this.nom = nom;
        this.description = description;
        this.questions = new ArrayList<>();
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }
    
}
