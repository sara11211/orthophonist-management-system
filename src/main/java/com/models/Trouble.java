package com.models;

import java.io.Serializable;

public class Trouble implements Serializable {

    private static final long serialVersionUID = 1L;



    private String nomTrouble;
    private String typeTrouble;


    public Trouble (String nomTrouble, String typeTrouble) {
        this.nomTrouble = nomTrouble;
        this.typeTrouble = typeTrouble;
    }
    public void setNomTrouble(String nomTrouble) {
        this.nomTrouble = nomTrouble;
    }

    public void setTypeTrouble(String typeTrouble) {
        this.typeTrouble = typeTrouble;
    }

    public String getNomTrouble() {
        return nomTrouble;
    }

    public String getTypeTrouble() {
        return typeTrouble;
    }
}