package com;
import com.models.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Scene;
import static com.HelloApplication.utilisateurCourant;




public class RechercheDossierController {

    @FXML
    private TextField searchField;
    @FXML
    private void handleSearchButtonAction() {
        String input = searchField.getText();
        try {
            Long patientId = Long.parseLong(input);
            if (utilisateurCourant.getPatientDossierHashMap().containsKey(patientId)) {
                Patient patient = utilisateurCourant.getPatientDossierHashMap().get(patientId);
                showPatientDetails(patient);
            }
            else {
                showAlert("Patient not found", "No patient found with ID " + patientId);
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid Long number");
        }
    }

    private void showPatientDetails(Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsultDossierPage.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            DetailsPatientController controller = loader.getController();
            controller.setPatient(patient);
            controller.setCalledForPatient(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


}