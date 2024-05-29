package com.models;

import java.util.HashMap;
import java.util.Map;

public class CompteRendu {

    private Map<Question, Integer> scores;

    public CompteRendu() {
        this.scores = new HashMap<>();
    }

    public void ajouterScore(Question question, int score) {
        scores.put(question, score);
    }

    public Integer getScore(Question question) {
        return scores.get(question); 
    }

    public void evaluer(QuestionLibre question, int score) {
        ajouterScore(question,score);
    }
    

}
