package com.models;

import java.io.Serializable;

public class Question implements Serializable{

    private String enonce;
    private int score;
    private static final long serialVersionUID = 1L;

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