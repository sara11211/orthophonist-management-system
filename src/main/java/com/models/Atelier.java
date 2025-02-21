package com.models;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.time.LocalTime;
import java.time.Duration;
public class Atelier extends RDV implements Serializable {
    private static final long serialVersionUID = 1L;

    private String thematique;
    private HashSet<Long> listeNumDossier;


    public Atelier(LocalDate date, LocalTime heureDebut, Duration duree, String infoSup, boolean isInfoSup, String thematique, HashSet<Long> listeNumDossier) {
        super(date, heureDebut, duree, infoSup, isInfoSup);
        this.thematique = thematique;
        this.listeNumDossier = listeNumDossier;
    }

    public void setThematique(String thematique) {
        this.thematique = thematique;
    }

    public void setListeNumDossier(HashSet<Long> listeNumDossier) {
        this.listeNumDossier = listeNumDossier;
    }

    public HashSet<Long> getListeNumDossier() {
        return listeNumDossier;
    }

    public String getThematique() {
        return thematique;
    }

    @Override
    public String toString() {
        return "Atelier : " + super.toString() ;
    }
    @Override
    public String getDetailedInfo() {
        StringBuilder dossierNums = new StringBuilder();
        for (Long numDossier : listeNumDossier) {
            dossierNums.append(numDossier).append(" ");
        }
        return super.getDetailedInfo() +
                "Type du rendez-vous : Atelier\n" +
                "Thématique de l'atelier : " + thematique + "\n" +
                "Liste des numéros de dossier des patients : " + dossierNums.toString() + "\n";
    }
}