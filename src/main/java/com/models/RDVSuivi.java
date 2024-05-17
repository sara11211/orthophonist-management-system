package com.models;
import java.time.LocalDate;
import java.util.HashSet;
import java.time.LocalTime;
import java.time.Duration;
public class RDVSuivi extends RDV {

    private Long numDossier;
    private boolean isPresentiel; // vrai : pour RDV en présentiel


    public RDVSuivi(LocalDate date, LocalTime heureDebut, Duration duree, String infoSup, boolean isInfoSup, Long numDossier, boolean isPresentiel) {
        super(date, heureDebut, duree, infoSup, isInfoSup);
        this.numDossier = numDossier;
        this.isPresentiel = isPresentiel;
    }

    public void setNumDossier(Long numDossier) {
        this.numDossier = numDossier;
    }

    public void setIsPresentiel(boolean isPresentiel) {
        this.isPresentiel = isPresentiel;
    }

    public Long getNumDossier() {
        return numDossier;
    }

    public boolean getIsPresentiel() {
        return isPresentiel;
    }

    public boolean scheduleRDV() {
        super.scheduleRDV();
        return true;
        // add stuff here


    }
}
