package com.models;
import java.time.LocalDate;
public class Enfant extends Patient {

    public enum ClasseEtude {
        PREPARATOIRE,
        AP1, AP2, AP3, AP4, AP5,
        AM1, AM2, AM3, AM4,
        AS1, AS2, AS3
    }

    private ClasseEtude classeEtude; 
    private String numTelParent;

    public Enfant(String nom, String prenom, LocalDate dateNaissance, String lieuNaissance, String adresse, ClasseEtude classeEtude, String numTelParent) {
        super(nom, prenom, dateNaissance, lieuNaissance, adresse);
        this.classeEtude = classeEtude;
        this.numTelParent = numTelParent;
    }

    public void setClasseEtude(ClasseEtude classeEtude) {
        this.classeEtude = classeEtude;
    }

    public void setNumTelParent(String numTelParent) {
        this.numTelParent = numTelParent;
    }

    public ClasseEtude getClasseEtude() {
        return classeEtude;
    }

    public String getNumTelParent() {
        return numTelParent;
    }

    public String toString(){
        return super.toString() + ", Classe d'étude : " + this.classeEtude.toString() + ", Numéro téléphone du parent : " + this.numTelParent ; 
    }
}
