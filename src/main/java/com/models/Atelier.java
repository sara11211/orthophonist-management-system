package com.models;
import java.time.LocalDate;
import java.util.HashSet;
import java.time.LocalTime;
import java.time.Duration;
public class Atelier extends RDV {

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

/*
    public boolean scheduleRDV() {
        // Implement logic to schedule an atelier meeting
        System.out.println("Rendez-vous Atelier avec thème: " + thematique);
        System.out.println("Patients:");
        for (Long folderNumber : listeNumDossier) {
            System.out.println("- Patient N°" + folderNumber);
        }
        super.scheduleRDV();
        return true;
    }
*/

}