package com.models;

import java.util.concurrent.atomic.AtomicLong;
import java.util.HashSet;
public class Dossier {
    private static final AtomicLong uniqueIdGenerator = new AtomicLong(1);
    private final long numDossier;
    private HashSet<RDV> rdvs;
    private HashSet<FicheSuivi> ficheSuivis;


    public Dossier() {
        this.numDossier = uniqueIdGenerator.getAndIncrement();
    }


    public Dossier(HashSet<FicheSuivi> ficheSuivis) {
        this.ficheSuivis = ficheSuivis;
        this.numDossier = uniqueIdGenerator.getAndIncrement();
    }

    public void setFicheSuivis(HashSet<FicheSuivi> ficheSuivis) {
        this.ficheSuivis = ficheSuivis;
    }

    public HashSet<FicheSuivi> getFicheSuivis() {
        return ficheSuivis;
    }

    public long getNumDossier() {
        return numDossier;
    }




}
