package com.models;

public class EpreuveClinique {
    
    private String observationClinique;
    private Test test;

    public EpreuveClinique (Test test, String observationClinique) {
        this.observationClinique = observationClinique;
        this.test= test;
    }

    public void setObservationClinique(String observationClinique) {
        this.observationClinique = observationClinique;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getObservationClinique() {
        return observationClinique;
    }

    public Test getTest() {
        return test;
    }
}
