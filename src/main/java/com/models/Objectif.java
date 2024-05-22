package com.models;

public class Objectif {
    public enum TypeObjectif {
        CourtTerme, MoyenTerme, LongTerme
    }
    private String nomObjectif;
    private TypeObjectif typeObjectif;
    private boolean isDone;

    public Objectif(String nomObjectif, TypeObjectif typeObjectif, boolean isDone) {
        this.nomObjectif = nomObjectif;
        this.typeObjectif = typeObjectif;
        this.isDone = isDone;
    }

    public void setNomObjectif(String nomObjectif) {
        this.nomObjectif = nomObjectif;
    }

    public void setTypeObjectif(TypeObjectif typeObjectif) {
        this.typeObjectif = typeObjectif;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getNomObjectif() {
        return nomObjectif;
    }

    public boolean getIsDone() {
        return isDone;
    }


    public TypeObjectif getTypeObjectif() {
        return typeObjectif;
    }



}