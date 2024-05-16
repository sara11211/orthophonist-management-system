package com.models;
import java.util.HashSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class RDV {
    /*public enum TypeRDV {
        Consultation, RDVSuivi, Atelier
    }
    private TypeRDV typeRDV;*/
    private LocalTime date;
    private LocalTime heureDebut;
    private Duration duree;
    private String infoSup;
    private boolean isInfoSup;


    public RDV(LocalTime date, LocalTime heureDebut, Duration duree, String infoSup) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.duree = duree;
        this.infoSup = infoSup;
    }

 
    public void setDate(LocalTime date) {
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


    public LocalTime getDate() {
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


    public boolean scheduleRDV() {
        System.out.println("Prévu pour le : "+date);
        System.out.println("A partir de : "+heureDebut);
        System.out.println("Jusqu'au : "+heureDebut.plus(duree));
        if (isInfoSup) System.out.println("Quelques informations en plus : "+infoSup);
        return true;
    }



}
