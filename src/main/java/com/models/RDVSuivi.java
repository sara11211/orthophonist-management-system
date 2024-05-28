package com.models;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.time.LocalTime;
import java.time.Duration;
public class RDVSuivi extends RDV implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long numDossier;
    private boolean isPresentiel; // vrai : pour RDV en présentiel
    private FicheSuivi ficheSuivi;


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

    public void setFicheSuivi(FicheSuivi ficheSuivi) {
        this.ficheSuivi = ficheSuivi;
    }

    public Long getNumDossier() {
        return numDossier;
    }

    public boolean getIsPresentiel() {
        return isPresentiel;
    }

    public FicheSuivi getFicheSuivi() {
        return ficheSuivi;
    }

    /*
    public boolean scheduleRDV() {
        super.scheduleRDV();
        return true;
        // add stuff here


    }*/

    @Override
    public String toString() {
        return
                "Séance de suivi : " + super.toString() ;
    }
    @Override
    public String getDetailedInfo() {
        return super.getDetailedInfo() +
                "Type du rendez-vous  : Séance de suivi\n" +
                (isPresentiel ? "Ce rendez-vous est en présentiel\n" : "Ce rendez-vous est en ligne\n") +
                "Ce rendez-vous est pour le patient N°" + numDossier + "\n";
    }


}