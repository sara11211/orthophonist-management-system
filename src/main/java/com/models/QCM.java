package com.models;

import java.io.Serializable;

public class QCM extends Question implements Serializable {

    private Proposition[] options;

    public QCM (String enonce, Proposition[] options) {
        super(enonce);
        this.options = options;
    }
    
    public Proposition[] getOptions() {
        return options;
    }
}
