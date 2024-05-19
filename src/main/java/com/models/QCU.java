package com.models;

import java.io.Serializable;

public class QCU extends Question implements Serializable {

    private Proposition[] options;

    public QCU (String enonce, Proposition[] options) {
        super(enonce);
        this.options = options;
    }
    
    public Proposition[] getOptions() {
        return options;
    }
}