package com.models;

public class Proposition {

    private String enonce;
    private int score;

    public Proposition(String enonce, int score) throws ScoreOutOfRangeException {
        if (score < 1 || score > 10) {
            throw new ScoreOutOfRangeException("Le score doit être compris entre 1 et 10");
        }
        this.enonce = enonce;
        this.score = score;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public void setScore(int score) throws ScoreOutOfRangeException {
        if (score < 1 || score > 10) {
            throw new ScoreOutOfRangeException("Le score doit être compris entre 1 et 10");
        }
        this.score = score;
    }

    public String getEnonce() {
        return enonce;
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return "L'énonce : " + this.enonce + ", Le score attribué : " + this.score;
    }
}