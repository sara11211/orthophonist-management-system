package com.models;

import java.io.Serializable;

public class Orthophoniste implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nom;
    private String prenom;
    private String adresse;
    private String numTel;
    private String adresseEmail;
    private String motDePasse;

    public Orthophoniste(String nom, String prenom, String adresse, String numTel, String adresseEmail, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.adresseEmail = adresseEmail;
        this.motDePasse = motDePasse;
    }

    public Orthophoniste(String adresseEmail, String motDePasse) {
        this.adresseEmail = adresseEmail;
        this.motDePasse = motDePasse;
    }

    public Orthophoniste(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "Orthophoniste{" +
                "nom= '" + nom + '\'' +
                ", prenom= '" + prenom + '\'' +
                ", adresse= '" + adresse + '\'' +
                ", numTel= '" + numTel + '\'' +
                ", adresseEmail= '" + adresseEmail + '\'' +
                ", motDePasse= '" + motDePasse + '\'' +
                '}';
    }
}
