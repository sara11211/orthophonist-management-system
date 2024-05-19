package com;

import com.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.scene.control.CheckBox;
import com.models.Consultation;
import javafx.fxml.Initializable;
import java.net.URL;

import java.util.ResourceBundle;
import static com.HelloApplication.utilisateurCourant;
public class DossierController {

    @FXML
    private TextField numDossierField;

    @FXML
    private TextArea dossierResult;

    @FXML
    private void handleSubmit(ActionEvent event) {
        String numDossier = numDossierField.getText();
        String printingRes;
        try {
            Long numDossierLong = Long.parseLong(numDossier);
            if (utilisateurCourant.getPatientDossierHashMap().containsKey(numDossierLong)) {
                Patient patient = utilisateurCourant.getPatientDossierHashMap().get(numDossierLong);
                for (RDV rdv : patient.getRdvs()) {
                    printingRes = rdv.giveRDVInfo();
                    dossierResult.appendText(printingRes + "\n");
                    dossierResult.setVisible(true);
                }
            } else {
                dossierResult.appendText("No patient found with dossier number: " + numDossier + "\n");
            }
        } catch (NumberFormatException e) {
            dossierResult.appendText("Invalid dossier number: " + numDossier + "\n");
        }
    }

    @FXML
    private void handleCreerPatient(ActionEvent event) {
        // 1. CREATION PATIENT AVEC REPONSES DE CREERPATIENTPAGE.FXML
        // 2. "AJOUTER RDV" AVEC LINKAGE VERS LE POPUP (CONSULTATION, RDVSUIVI, ATELIER) SOUS FORM FXML
        // 3. TESTER DOSSIER : CREER UN PATIENT ET DES RDVS POUR PATIENTS (AVEC ENREGISTREMENT DES RDVS DANS PATIENT
        // APRES TEST D'AFFICHAGE DES RDVS DE CE PATIENT EN MEME EXECUTION (PAS DE SAUVEGARDE)
    }
}
