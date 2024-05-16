package com.models;
import java.time.LocalTime;
import java.time.Duration;
public class Consultation extends RDV {
    private String nomPatient;
    private String prenomPatient;
    private int agePatient;

    public Consultation(LocalTime date, LocalTime heureDebut, Duration duree, String infoSup, String nomPatient, String prenomPatient, int agePatient) {
        super(date, heureDebut, duree, infoSup);
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
        this.agePatient = agePatient;
    }

    public boolean scheduleRDV() {
            System.out.println("Consultation orthophoniste : ");
            System.out.println("Patient concerné: " + nomPatient+ " "+prenomPatient+" Agé(e) de : "+agePatient);
            System.out.println("Date: " + super.getDate());
            System.out.println("Heure debut: " + super.getHeureDebut());
            System.out.println("Duree: " + super.getDuree());
            System.out.println("Informations en plus: " + super.getInfoSup());
            System.out.println("Consultation programmée avec succès.");
            super.scheduleRDV();
            return true;

    }

}