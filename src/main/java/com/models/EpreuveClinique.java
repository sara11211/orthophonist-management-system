package com.models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class EpreuveClinique implements Serializable {
    
    private String observationClinique;
    private List<Test> tests;

    public EpreuveClinique (String observationClinique) {
        this.observationClinique = observationClinique;
        tests = new ArrayList<>();
    }

    public void setObservationClinique(String observationClinique) {
        this.observationClinique = observationClinique;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public String getObservationClinique() {
        return observationClinique;
    }

    public List<Test> getTests() {
        return tests;
    }
}
