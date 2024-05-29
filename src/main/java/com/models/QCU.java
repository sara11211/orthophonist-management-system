package com.models;

import java.io.Serializable;

public class QCU extends Question implements Serializable {

    private Proposition[] options;
    private static final long serialVersionUID = 1L;
    private int score;

    public QCU (String enonce, Proposition[] options) {
        super(enonce);
        this.options = options;
    }

    public QCU (String enonce, int score, Proposition[] options) {
        super(enonce);
        this.options = options;
        this.score = score;
    }

    
    public Proposition[] getOptions() {
        return options;
    }
}
