package com.models;

import java.io.Serializable;
import java.util.Objects;

public class Objectif implements Serializable {

    private static final long serialVersionUID = 1L;
    public enum TypeObjectif {
        CourtTerme, MoyenTerme, LongTerme
    }
    private String nomObjectif;
    private String typeObjectif;
    private boolean isDone;

    public Objectif(String nomObjectif, String typeObjectif, boolean isDone) {
        this.nomObjectif = nomObjectif;
        this.typeObjectif = typeObjectif;
        this.isDone = isDone;
    }

    public void setNomObjectif(String nomObjectif) {
        this.nomObjectif = nomObjectif;
    }

    public void setTypeObjectif(String typeObjectif) {
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


    public String getTypeObjectif() {
        return typeObjectif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objectif objectif = (Objectif) o;
        return Objects.equals(nomObjectif, objectif.nomObjectif) &&
                Objects.equals(typeObjectif, objectif.typeObjectif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomObjectif, typeObjectif);
    }



}