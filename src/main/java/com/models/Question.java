package com.models;

import java.io.Serializable;

public class Question implements Serializable{

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