package com;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SideBarController {

    @FXML
    private Button testAnamneseButton;

    @FXML
    private Button patientFilesButton;

    @FXML
    private Button appointmentsButton;

    @FXML
    private Button statisticsButton;

    @FXML
    public void handleTestAnamnese(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("options.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handlePatientFiles() {
        System.out.println("Navigating to Patient Files");
        // Implement navigation logic
    }

    @FXML
    public void handleAppointments() {
        System.out.println("Navigating to Appointments");
        // Implement navigation logic
    }

    @FXML
    public void handleStatistics() {
        System.out.println("Navigating to Statistics");
        // Implement navigation logic
    }
}
