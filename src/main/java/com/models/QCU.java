package com.models;

public class QCU extends Question {

    private Proposition[] options;

    public QCU (String enonce, Proposition[] options) {
        super(enonce);
        this.options = options;
    }
    
    public Proposition[] getOptions() {
        return options;
    }
}