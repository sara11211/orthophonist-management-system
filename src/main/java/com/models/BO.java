package com.models;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.time.LocalDate;
public class BO implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDate dateBO;
    private EpreuveClinique epreuvesCliniques;
    private Diagnostic diagnostic;
    private String projetTherap;

    public BO() {


    }
    public BO(LocalDate dateBO, EpreuveClinique epreuvesCliniques, Diagnostic diagnostic, String projetTherap) {
        this.dateBO = dateBO;
        this.diagnostic = diagnostic;
        this.epreuvesCliniques = epreuvesCliniques;
        this.projetTherap = projetTherap;
    }

    public void setDateBO(LocalDate dateBO) {
        this.dateBO = dateBO;
    }

    public void setDiagnostic(Diagnostic diagnostic) {
        this.diagnostic = diagnostic;
    }

    public void setEpreuvesCliniques(EpreuveClinique epreuvesCliniques) {
        this.epreuvesCliniques = epreuvesCliniques;
    }

    public void setProjetTherap(String projetTherap) {
        this.projetTherap = projetTherap;
    }

    public LocalDate getDateBO() {
        return dateBO;
    }

    public Diagnostic getDiagnostic() {
        return diagnostic;
    }

    public EpreuveClinique getEpreuvesCliniques() {
        return epreuvesCliniques;
    }

    public String getProjetTherap() {
        return projetTherap;
    }
}
