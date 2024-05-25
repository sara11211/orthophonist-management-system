package com.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    private HashSet<Test> tests;
    private Planning planning;
    private HashMap<Long, Patient> patientDossierHashMap;
    private static final long serialVersionUID = 1L;

    // constructors
    public Orthophoniste(String nom, String prenom, String adresse, String numTel, String adresseEmail, String motDePasse, Planning planning) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.adresseEmail = adresseEmail;
        this.motDePasse = motDePasse;
        this.anamneses = new ArrayList<>();
        this.tests = new HashSet<>();
        this.planning = planning;
        patientDossierHashMap = new HashMap<>();
    }

    public Orthophoniste(String adresseEmail, String motDePasse) {
        this.adresseEmail = adresseEmail;
        this.motDePasse = motDePasse;
        this.anamneses = new ArrayList<>();
        this.tests = new HashSet<>();
        patientDossierHashMap = new HashMap<>();
    }

    public Orthophoniste(String adresseEmail) {
        this.adresseEmail = adresseEmail;
        this.anamneses = new ArrayList<>();
        this.tests = new HashSet<>();
        patientDossierHashMap = new HashMap<>();
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

    public void setTests(HashSet<Test> tests) {
        this.tests = tests;
    }

    public void addTest(Test test) {
        tests.add(test);
    }

    public HashSet<Test> getTests() {
        return tests;
    }

    public void setPatientDossierHashMap(HashMap<Long, Patient> patientDossierHashMap) {
        this.patientDossierHashMap = patientDossierHashMap;
    }

    public HashMap<Long, Patient> getPatientDossierHashMap() {
        return patientDossierHashMap;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    public Planning getPlanning() {
        return planning;
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
