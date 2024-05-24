package com.models;

import java.io.Serializable;

public class Question implements Serializable{

    private String enonce;
    private int score;

    public Question(String enonce){
        this.enonce = enonce;
    }

    public String getEnonce() {
        return enonce;
    }
    
    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}