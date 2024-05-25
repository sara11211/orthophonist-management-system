package com.models;

import java.io.Serializable;

public class QuestionLibre extends Question implements Serializable{

    private int score;
    private static final long serialVersionUID = 1L;

    public QuestionLibre(String enonce, int score) {
        super(enonce);
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}