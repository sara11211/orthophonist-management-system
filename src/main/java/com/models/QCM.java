package com.models;

public class QCM extends Question {

    private Proposition[] options;

    public QCM (String enonce, Proposition[] options) {
        super(enonce);
        this.options = options;
    }
    
    public Proposition[] getOptions() {
        return options;
    }
}
