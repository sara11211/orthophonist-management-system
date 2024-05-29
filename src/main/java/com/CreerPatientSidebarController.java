package com;

import com.models.Dossier;
import com.models.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.HelloApplication.utilisateurCourant;

public class CreerPatientSidebarController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label message;
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private DatePicker dateNaissanceField;

    @FXML
    private TextField lieuNaissanceField;

    @FXML
    private TextField adresseField;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        LocalDate dateNaissance = dateNaissanceField.getValue();
        String lieuNaissance = lieuNaissanceField.getText();
        String adresse = adresseField.getText();

        Patient patient = new Patient(firstName, lastName, dateNaissance, lieuNaissance, adresse);
        Dossier dossier = new Dossier(patient);
        utilisateurCourant.getPatientDossierHashMap().put(dossier.getNumDossier(), patient);
        // Close the current stage (screen)
        if (utilisateurCourant.getPatientDossierHashMap().containsKey(dossier.getNumDossier())) {
            message.setText("Patient ajouté à la liste");
        } else {
            message.setText("Erreur creéation du patient.");
        }
        System.out.println("Patient created successfully with ID "+dossier.getNumDossier());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("first_page.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}