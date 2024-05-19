package com.models;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
public class Consultation extends RDV {
    private String nomPatient;
    private String prenomPatient;
    private int agePatient;

    public Consultation(LocalDate date, LocalTime heureDebut, Duration duree, String infoSup, boolean isInfoSup, String nomPatient, String prenomPatient, int agePatient) {
        super(date, heureDebut, duree, infoSup, isInfoSup);
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
        this.agePatient = agePatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public void setPrenomPatient(String prenomPatient) {
        this.prenomPatient = prenomPatient;
    }

    public void setAgePatient(int agePatient) {
        this.agePatient = agePatient;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public String getPrenomPatient() {
        return prenomPatient;
    }

    public int getAgePatient() {
        return agePatient;
    }
/*
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
*/
}