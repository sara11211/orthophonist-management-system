package com.models;

public class Patient {
    
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String lieuNaissance;
    private String adresse;

    public Patient(String nom, String prenom, String dateNaissance, String lieuNaissance, String adresse){
        this.nom =  nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.adresse = adresse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String toString() {
        return "Nom : " + this.nom + ", Prenom : " + this.prenom + ", Date de naissance : " + this.dateNaissance + ", Lieu de naissance : " + this.lieuNaissance + ", Adresse : " + this.adresse;
    }

    // implementation des methodes repondre aux questions
}