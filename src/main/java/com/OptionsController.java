package com;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OptionsController {

    @FXML
    private void handleViewAnamneses(ActionEvent event) {
        loadScene(event, "anamneses_display.fxml");
    }

    @FXML
    private void handleCreateAnamnese(ActionEvent event) {
        loadScene(event, "anamnese_creation.fxml");
    }

    @FXML
    private void handleCreateTest(ActionEvent event) {
        loadScene(event, "test_creation.fxml");
    }

    @FXML
    private void handleViewTests(ActionEvent event) {
        loadScene(event, "view_tests.fxml");
    }

    private void loadScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
