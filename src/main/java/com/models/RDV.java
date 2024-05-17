package com.models;
import java.time.LocalDate;
import java.util.HashSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class RDV {
    /*public enum TypeRDV {
        Consultation, RDVSuivi, Atelier
    }
    private TypeRDV typeRDV;*/
    private LocalDate date;
    private LocalTime heureDebut;
    private Duration duree;
    private String infoSup;
    private boolean isInfoSup;


    public RDV(LocalDate date, LocalTime heureDebut, Duration duree, String infoSup, boolean isInfoSup) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.duree = duree;
        this.infoSup = infoSup;
        this.isInfoSup = isInfoSup;
    }

 
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setDuree(Duration duree) {
        this.duree = duree;
    }

    public void setInfoSup(String infoSup) {
        this.infoSup = infoSup;
    }

    public void setInfoSup(boolean infoSup) {
        isInfoSup = infoSup;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public Duration getDuree() {
        return duree;
    }

    public String getInfoSup() {
        return infoSup;
    }

    public boolean getIsInfoSup() { return isInfoSup; }

    public boolean scheduleRDV() {
        System.out.println("Prévu pour le : "+date);
        System.out.println("A partir de : "+heureDebut);
        System.out.println("Jusqu'au : "+heureDebut.plus(duree));
        if (isInfoSup) System.out.println("Quelques informations en plus : "+infoSup);
        return true;
    }



}
