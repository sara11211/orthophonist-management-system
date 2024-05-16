package com.models;
import java.util.HashSet;
import java.time.LocalTime;
import java.time.Duration;
public class Atelier extends RDV {

    private String thematique;
    private HashSet<Long> listeNumDossier;


    public Atelier(LocalTime date, LocalTime heureDebut, Duration duree, String infoSup, String thematique, HashSet<Long> listeNumDossier) {
        super(date, heureDebut, duree, infoSup);
        this.thematique = thematique;
        this.listeNumDossier = listeNumDossier;
    }

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


}