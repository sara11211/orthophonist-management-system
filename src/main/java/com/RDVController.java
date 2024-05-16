package com;
import com.models.Patient;
import com.models.Consultation;
import com.models.Orthophoniste;
import java.time.LocalTime;
import java.time.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RDVController {



        // when user clicks on add consultation. redirection + function returns what calendar needs
        public void scheduleConsultation(LocalTime date, LocalTime heureDebut, Duration duree, String infoSup, String nomPatient, String prenomPatient, int agePatient) {
            Consultation consultation = new Consultation(date, heureDebut, duree, infoSup, nomPatient, prenomPatient, agePatient);
            consultation.scheduleRDV();
        }

/*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categorieChoiceBox.getItems().addAll(Categorie.STUDIES, Categorie.WORK, Categorie.HOBBY, Categorie.SPORT, Categorie.HEALTH, Categorie.OTHER);
        prioriteChoiceBox.getItems().addAll(Priorite.LOW, Priorite.MEDIUM, Priorite.HIGHT);

        decomposable.selectedProperty().addListener((observable, oldValue, newValue) -> {// Hide or show the periodicite TextField based on the CheckBox state
            periodicite.setVisible(!newValue);
        });

        // Add a listener to the ajouterProjet CheckBox
        ajouterProjet.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // Show or hide the nomProjet TextField based on the CheckBox state
            nomProjet.setVisible(newValue);
        });


    }
    @FXML
    public void handleCancelButtonAction(ActionEvent event) {
        // Reset the UI elements or close the window without saving any changes
        clearFields();
        closeWindow();
    }*/
}
