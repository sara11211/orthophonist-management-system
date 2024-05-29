package com.models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.time.LocalDate;
public class Patient implements Serializable  {
    private static final long serialVersionUID = 1L;

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String lieuNaissance;
    private String adresse;

    private List<RDV> rdvs;

    private List<BO> bos;

    private BOPremier boPremier;

    private FicheSuivi ficheSuivi;


    public Patient(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.rdvs = new ArrayList<>();
        this.bos = new ArrayList<>();
        //this.ficheSuivi = fiche
    }

    public Patient(String nom, String prenom, LocalDate dateNaissance, String lieuNaissance, String adresse){
        this.nom =  nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.adresse = adresse;
        this.bos = new ArrayList<>();
        this.rdvs = new ArrayList<>();
        this.boPremier = new BOPremier();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setRdvs(List<RDV> rdvs) {
        this.rdvs = rdvs;
    }

    public void setBos(List<BO> bos) {
        this.bos = bos;
    }

    public void setBoPremier(BOPremier boPremier) {
        this.boPremier = boPremier;
    }

    public void setFicheSuivi(FicheSuivi ficheSuivi) {
        this.ficheSuivi = ficheSuivi;
    }


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public List<RDV> getRdvs() {
        return rdvs;
    }

    public List<BO> getBos() {
        return bos;
    }

    public BOPremier getBoPremier() {
        return boPremier;
    }

    public FicheSuivi getFicheSuivi() {
        return ficheSuivi;
    }

    public String toString() {
        return "Nom : " + this.nom + ", Prenom : " + this.prenom + ", Date de naissance : " + this.dateNaissance + ", Lieu de naissance : " + this.lieuNaissance + ", Adresse : " + this.adresse;
    }

}
