package com.models;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class Session implements Decomposable<Session>, Serializable {

    protected LocalDateTime heureDebut;
    protected LocalDateTime heureFin;
    protected Duration duree;

    public Session(LocalDateTime heureDebut, LocalDateTime heureFin) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.duree = Duration.between(heureDebut, heureFin);
    }


    public Duration getDurationMin() {
        return duree;
    }


    @Override
    public Session decompose(Session obj) {
        return null;// a revoir
    }

    @Override
    public String toString() {
        return "heureDebut=" + heureDebut +
                "    heureFin=" + heureFin +
                "    duree=" + duree +
                '}';
    }



    /**************** getters and setters *********/
    public LocalDateTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalDateTime hDebut) {
        this.heureDebut = hDebut;
    }

    public LocalDateTime getHeureFin() {
        return heureFin;
    }

    public void sethFin(LocalDateTime hFin) {
        this.heureFin = hFin;
    }

    public Duration getDuree() {
        return duree;
    }

    public void setDuree(Duration duree) {
        this.duree = duree;
    }
}
