package com.models;

import java.util.ArrayList;
import java.util.List;


public class Anamnese {

    private List<Question> questions; //ajouter methode filtre

    public Anamnese() {
        this.questions = new ArrayList<>();
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
