package com.models;
import java.time.LocalDate;
public class BOPremier extends BO {
    private Anamnese anamnese;
    public BOPremier(LocalDate dateBO, Anamnese anamnese, EpreuveClinique epreuveCliniques, Diagnostic diagnostic, String projetTherap) {
        super(dateBO,epreuveCliniques, diagnostic, projetTherap);
        this.anamnese = anamnese;
    }

    @Override
    public void setDiagnostic(Diagnostic diagnostic) {
        super.setDiagnostic(diagnostic);
    }

    @Override
    public void setEpreuvesCliniques(EpreuveClinique epreuvesCliniques) {
        super.setEpreuvesCliniques(epreuvesCliniques);
    }

    @Override
    public void setProjetTherap(String projetTherap) {
        super.setProjetTherap(projetTherap);
    }

    public void setAnamnese(Anamnese anamnese) {
        this.anamnese = anamnese;
    }

    @Override
    public Diagnostic getDiagnostic() {
        return super.getDiagnostic();
    }

    @Override
    public EpreuveClinique getEpreuvesCliniques() {
        return super.getEpreuvesCliniques();
    }

    @Override
    public String getProjetTherap() {
        return super.getProjetTherap();
    }

    public Anamnese getAnamnese() {
        return anamnese;
    }
}
