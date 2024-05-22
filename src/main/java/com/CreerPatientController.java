package com;
import com.models.Consultation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static com.HelloApplication.utilisateurCourant;
import com.models.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import java.time.LocalTime;
import java.time.Duration;
public class CreerPatientController implements Initializable {
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

    // CREATION D'UN DOSSIER PATIENT
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
        Stage currentStage = (Stage) firstNameField.getScene().getWindow();
        currentStage.close();

        // Optionally, perform additional actions after closing the stage
        // For example, show a confirmation message
        System.out.println("Patient created successfully with ID "+dossier.getNumDossier());
        // oms.sauvegarde;
    }
}