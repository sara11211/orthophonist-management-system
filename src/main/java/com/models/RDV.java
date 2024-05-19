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

    public String giveRDVInfo() {
        StringBuilder message = new StringBuilder();
        message.append("----- Rendez-vous plannifié -----\n");
        message.append("Date du rendez-vous  : ").append(this.getDate()).append("\n");
        message.append("Heure du rendez-vous  : ").append(this.getHeureDebut()).append("\n");
        message.append("Heure de fin du rendez-vous  : ").append(this.getHeureDebut().plus(this.getDuree())).append("\n");

        if (this.getIsInfoSup()) {
            message.append("Informations supplémentaires : ").append(this.getInfoSup()).append("\n");
        }

        if (this instanceof Consultation) {
            Consultation consultation = (Consultation) this;
            message.append("Type du rendez-vous  : Consultation\n");
            message.append("Nom et prénom du patient  : ").append(consultation.getNomPatient()).append(" ").append(consultation.getPrenomPatient()).append("\n");
            message.append("Age du patient : ").append(consultation.getAgePatient()).append("\n");
        }

        if (this instanceof Atelier) {
            Atelier atelier = (Atelier) this;
            message.append("Type du rendez-vous  : Atelier\n");
            message.append("Thématique de l'atelier : ").append(atelier.getThematique()).append("\n");
            message.append("Liste des numéros de dossier des patients : ");
            for (Long numDossier : atelier.getListeNumDossier()) {
                message.append(numDossier).append(" ");
            }
            message.append("\n");
        }

        if (this instanceof RDVSuivi) {
            RDVSuivi rdvSuivi = (RDVSuivi) this;
            message.append("Type du rendez-vous  : Séance de suivi\n");
            if (rdvSuivi.getIsPresentiel()) {
                message.append("Ce rendez-vous est en présentiel\n");
            } else {
                message.append("Ce rendez-vous est en ligne\n");
            }
            message.append("Ce rendez-vous est pour le patient N°").append(rdvSuivi.getNumDossier()).append("\n");
        }

        message.append("---------------------------------\n");

        return message.toString();
    }



}
