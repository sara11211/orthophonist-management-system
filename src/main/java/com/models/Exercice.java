package com.models;

public class Exercice {
    private String consigne;
    private String materiel;
    private int score;

    public Exercice(String consigne, String materiel, int score) {
        this.consigne = consigne;
        this.materiel = materiel;
        this.score = score;
    }

    public String getConsigne() {
        return consigne;
    }

    public String getMateriel() {
        return materiel;
    }
    
    public int getScore() {
        return score;
    }

    public void setConsigne(String consigne) {
        this.consigne = consigne;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
