package com.models;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
public class Consultation extends RDV implements Serializable {
    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return
                "Consultation : " +super.toString() ;
    }
    @Override
    public String getDetailedInfo() {
        return super.getDetailedInfo() +
                "Type du rendez-vous : Consultation\n" +
                "Nom et prénom du patient : " + nomPatient + " " + prenomPatient + "\n" +
                "Age du patient : " + agePatient + "\n";
    }
}