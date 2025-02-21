package com.models;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Planning implements Serializable {
    public Duration dureeMinCreneau;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private ArrayList<SessionLibre> sessionLibres;
    private ArrayList<SessionOccupee> sessionOccupees;
    private ArrayList<RDV> rdvsPlannified;
    private static final long serialVersionUID = 1L;


    public Planning(String nom, LocalDate dateDebut, LocalDate dateFin) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dureeMinCreneau = Duration.ofHours(1);
        ;
        this.sessionLibres = new ArrayList<>();
        this.sessionOccupees = new ArrayList<>();
        this.rdvsPlannified = new ArrayList<>();
        populateFreeSlots();
    }


    private void populateFreeSlots() {
        while (!dateDebut.isAfter(dateFin)) {
            LocalDateTime startTime = dateDebut.atStartOfDay();
            LocalDateTime endTime = dateFin.atTime(LocalTime.MAX);
            while (startTime.plus(dureeMinCreneau).isBefore(endTime) || startTime.plus(dureeMinCreneau).equals(endTime)) {
                sessionLibres.add(new SessionLibre(startTime, startTime.plus(dureeMinCreneau)));
                startTime = startTime.plus(dureeMinCreneau);
            }
            dateDebut = dateDebut.plusDays(1);
        }
    }




    public Duration getDureeMinCreneau() {
        return dureeMinCreneau;
    }

    public void setDureeMinCreneau(Duration dureeMinCreneau) {
        this.dureeMinCreneau = dureeMinCreneau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public ArrayList<SessionLibre> getSessionLibres() {
        return sessionLibres;
    }

    public void setSessionLibres(ArrayList<SessionLibre> sessionLibres) {
        this.sessionLibres.clear();
        this.sessionLibres.addAll(sessionLibres);
    }

    public ArrayList<SessionOccupee> getSessionOccupees() {
        return sessionOccupees;
    }

    public void setSessionOccupees(ArrayList<SessionOccupee> sessionOccupees) {
        this.sessionOccupees.clear();
        this.sessionOccupees.addAll(sessionOccupees);
    }


    public ArrayList<RDV> getRDVSPlannified(LocalDate j) {

        ArrayList<RDV> rdvsJJMM = new ArrayList<RDV>();

        for (RDV rdv: this.rdvsPlannified ) {
            if (rdv.getDate().equals(j))
            {
                rdvsJJMM.add(rdv);
            }
        }
        return rdvsJJMM;
    }

    public void setRDVSPlannified(ArrayList<RDV> rdvsPlannified) {
        this.rdvsPlannified.clear();
        this.rdvsPlannified.addAll(rdvsPlannified);
    }


    public boolean planifier(SessionLibre sessionLibre, RDV rdv) {

        if (rdv instanceof Consultation) {
            if (sessionLibre.getDuree().toHours() == rdv.getDuree().toHours())
            {
                SessionOccupee SessionOccupee = new SessionOccupee(sessionLibre.getHeureDebut(),sessionLibre.getHeureFin(),(Consultation) rdv);
                this.sessionLibres.remove(sessionLibre);
                this.rdvsPlannified.add(rdv);
                this.sessionOccupees.add(SessionOccupee);
                return true;
            } else if (sessionLibre.getDuree().toHours() > rdv.getDuree().toHours()) {

                LocalDateTime nvHourDebut = sessionLibre.getHeureDebut().plusHours(rdv.getDuree().toHours());
                SessionLibre creneauLibre1 = new SessionLibre(nvHourDebut,sessionLibre.getHeureFin());

                this.sessionLibres.remove(sessionLibre);
                this.sessionLibres.add(creneauLibre1);

                SessionOccupee SessionOccupee = new SessionOccupee(sessionLibre.getHeureDebut(),nvHourDebut,(Consultation) rdv);
                this.sessionLibres.remove(sessionLibre);
                this.rdvsPlannified.add(rdv);
                this.sessionOccupees.add(SessionOccupee);
                return true;
            }

            return false;
        } else if (rdv instanceof Atelier) {
            if (sessionLibre.getDuree().toHours() == rdv.getDuree().toHours())
            {
                SessionOccupee SessionOccupee = new SessionOccupee(sessionLibre.getHeureDebut(),sessionLibre.getHeureFin(),(Atelier) rdv);
                this.sessionLibres.remove(sessionLibre);
                this.rdvsPlannified.add(rdv);
                this.sessionOccupees.add(SessionOccupee);
                return true;
            } else if (sessionLibre.getDuree().toHours() > rdv.getDuree().toHours()) {

                LocalDateTime nvHourDebut = sessionLibre.getHeureDebut().plusHours(rdv.getDuree().toHours());
                SessionLibre creneauLibre1 = new SessionLibre(nvHourDebut,sessionLibre.getHeureFin());

                this.sessionLibres.remove(sessionLibre);
                this.sessionLibres.add(creneauLibre1);

                SessionOccupee SessionOccupee = new SessionOccupee(sessionLibre.getHeureDebut(),nvHourDebut,(Atelier) rdv);
                this.sessionLibres.remove(sessionLibre);
                this.rdvsPlannified.add(rdv);
                this.sessionOccupees.add(SessionOccupee);
                return true;
            }
            return false;
        } else if (rdv instanceof RDVSuivi) {
            if (sessionLibre.getDuree().toHours() == rdv.getDuree().toHours()) {
                SessionOccupee SessionOccupee = new SessionOccupee(sessionLibre.getHeureDebut(), sessionLibre.getHeureFin(), (RDVSuivi) rdv);
                this.sessionLibres.remove(sessionLibre);
                this.rdvsPlannified.add(rdv);
                this.sessionOccupees.add(SessionOccupee);
                return true;
            } else if (sessionLibre.getDuree().toHours() > rdv.getDuree().toHours()) {

                LocalDateTime nvHourDebut = sessionLibre.getHeureDebut().plusHours(rdv.getDuree().toHours());
                SessionLibre creneauLibre1 = new SessionLibre(nvHourDebut, sessionLibre.getHeureFin());

                this.sessionLibres.remove(sessionLibre);
                this.sessionLibres.add(creneauLibre1);

                SessionOccupee SessionOccupee = new SessionOccupee(sessionLibre.getHeureDebut(), nvHourDebut, (RDVSuivi) rdv);
                this.sessionLibres.remove(sessionLibre);
                this.rdvsPlannified.add(rdv);
                this.sessionOccupees.add(SessionOccupee);
                return true;
            }
            return false;
        }
        else return false;
    }


}