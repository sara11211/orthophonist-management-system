package com.models;

import java.util.concurrent.atomic.AtomicLong;
import java.util.HashSet;
public class Dossier {
    private static final AtomicLong uniqueIdGenerator = new AtomicLong(1);
    private final long numDossier;

    private Patient patient;



    public Dossier(Patient patient) {
        this.patient = patient;
        this.numDossier = uniqueIdGenerator.getAndIncrement();
    }


    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public long getNumDossier() {
        return numDossier;
    }




}