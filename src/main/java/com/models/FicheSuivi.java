package com.models;
import java.util.HashSet;

public class FicheSuivi {
    Patient patient;
    HashSet<Objectif> objectifs;

    public FicheSuivi(Patient patient, HashSet<Objectif> objectifs) {
        this.patient = patient;
        this.objectifs = objectifs;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setObjectifs(HashSet<Objectif> objectifs) {
        this.objectifs = objectifs;
    }

    public Patient getPatient() {
        return patient;
    }

    public HashSet<Objectif> getObjectifs() {
        return objectifs;
    }
}
