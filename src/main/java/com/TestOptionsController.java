package com;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TestOptionsController {
@FXML
    private Button createTestQuestionnaireButton;
    @FXML
    private Button createTestExerciceButton;

    @FXML
    private void handleCreateTestQuestionnaire(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("test_creation.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) createTestQuestionnaireButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCreateTestExercice(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("test_exercice_creation.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) createTestExerciceButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
