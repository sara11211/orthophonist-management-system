package com.models;
import java.util.HashSet;
import java.time.LocalTime;
import java.time.Duration;
public class RDVSuivi extends RDV {

    private Long numDossier;
    private boolean isPresentiel; // vrai : pour RDV en présentiel


    public RDVSuivi(LocalTime date, LocalTime heureDebut, Duration duree, String infoSup, Long numDossier, boolean isPresentiel) {
        super(date, heureDebut, duree, infoSup);
        this.numDossier = numDossier;
        this.isPresentiel = isPresentiel;
    }

    public boolean scheduleRDV() {
        super.scheduleRDV();
        return true;
        // add stuff here


    }
}
