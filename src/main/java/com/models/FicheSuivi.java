package com.models;
import java.io.Serializable;
import java.util.HashMap;

public class FicheSuivi implements Serializable {

    private static final long serialVersionUID = 1L;

    // objectif et son score
    HashMap<Objectif, Integer> objectifScores;

    public FicheSuivi(HashMap<Objectif, Integer> objectifScores) {
        this.objectifScores = objectifScores;
    }


    public void setObjectifScores(HashMap<Objectif, Integer> objectifScores) {
        this.objectifScores = objectifScores;
    }

    public HashMap<Objectif, Integer> getObjectifScores() {
        return objectifScores;
    }
}