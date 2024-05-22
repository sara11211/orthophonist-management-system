package com.models;

import java.time.LocalDate;
public class Adulte extends Patient {

    public enum Diplome {
        BAC,
        LICENCE,
        MASTER,
        DOCTORAT
    }

    private Diplome diplome;
    private String profession;
    private String numTel;

    public Adulte(String nom, String prenom, LocalDate dateNaissance, String lieuNaissance, String adresse, Diplome diplome, String profession, String numTel) {
        super(nom, prenom, dateNaissance, lieuNaissance, adresse);
        this.diplome = diplome;
        this.profession = profession;
        this.numTel = numTel;
    }

    public void setDiplome(Diplome diplome) {
        this.diplome = diplome;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Diplome getDiplome() {
        return diplome;
    }

    public String getProfession() {
        return profession;
    }

    public String getNumTel() {
        return numTel;
    }

    public String toString(){
        return super.toString() + ", Diplôme : " + this.diplome.toString() + ", Profession : " + this.profession + ", Numéro téléphone : " + this.numTel ; 
    }
}