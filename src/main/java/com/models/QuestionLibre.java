package com.models;

public class QuestionLibre extends Question {

    private int score;

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