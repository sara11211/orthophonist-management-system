package com.models;

public class Question {

    private String enonce;

    public Question(String enonce){
        this.enonce = enonce;
    }

    public String getEnonce() {
        return enonce;
    }
    
    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }
}
