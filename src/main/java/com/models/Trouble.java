package com.models;

public class Trouble {
    public enum Troubles {
        Déglutition, NeuroDéveloppementaux, TroublesCognitifs
    }

    private String nomTrouble;
    private Troubles typeTrouble;


    public void setNomTrouble(String nomTrouble) {
        this.nomTrouble = nomTrouble;
    }
    public void setTypeTrouble(Troubles typeTrouble) {
        this.typeTrouble = typeTrouble;
    }

    public String getNomTrouble() {
        return nomTrouble;
    }
    public Troubles getTypeTrouble() {
        return typeTrouble;
    }
}