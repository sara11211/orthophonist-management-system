package com.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Orthophoniste implements Serializable {
    // attributes
    private String nom;
    private String prenom;
    private String adresse;
    private String numTel;
    private String adresseEmail;
    private String motDePasse;
    private List<Anamnese> anamneses;

    // constructors
    public Orthophoniste(String nom, String prenom, String adresse, String numTel, String adresseEmail, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.adresseEmail = adresseEmail;
        this.motDePasse = motDePasse;
        this.anamneses = new ArrayList<>(); // Initialize the list
    }

    public Orthophoniste(String adresseEmail, String motDePasse) {
        this.adresseEmail = adresseEmail;
        this.motDePasse = motDePasse;
        this.anamneses = new ArrayList<>(); // Initialize the list
    }

    public Orthophoniste(String adresseEmail) {
        this.adresseEmail = adresseEmail;
        this.anamneses = new ArrayList<>(); // Initialize the list
    }

    // setters & getters
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

    public List<Anamnese> getAnamneses() {
        return anamneses;
    }

    public void addAnamnese(Anamnese anamnese) {
        anamneses.add(anamnese);
    }

    public void setAnamneses(List<Anamnese> anamneses) {
        this.anamneses = anamneses;
    }

    // Override methods
    @Override
    public String toString() {
        return "Orthophoniste { " +
                "nom= '" + nom + '\'' +
                ", prenom= '" + prenom + '\'' +
                ", adresse= '" + adresse + '\'' +
                ", numTel= '" + numTel + '\'' +
                ", adresseEmail= '" + adresseEmail + '\'' +
                ", motDePasse= '" + motDePasse + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Orthophoniste other = (Orthophoniste) obj;
        return getAdresseEmail().equals(other.getAdresseEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresseEmail, motDePasse);
    }
}